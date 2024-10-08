package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.create_batch;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAsset;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAssetRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions.ExceedsTotalAllowableShoeVariantAssets;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetPosition;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetUrl;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.FileUploadFailure;
import com.shoe_ecommerce.inventory.context.shared.domain.ports.services.storage.BlobStorageService;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;
import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public final class ShoeVariantAssetBatchCreator {

    private final ShoeVariantAssetRepository shoeVariantAssetRepository;
    private final ShoeVariantRepository shoeVariantRepository;

    private final BlobStorageService storageService;
    private final UuidGenerator uuidGenerator;

    public ShoeVariantAssetBatchCreator(
            ShoeVariantAssetRepository shoeVariantAssetRepository,
            ShoeVariantRepository shoeVariantRepository,
            BlobStorageService storageService,
            UuidGenerator uuidGenerator
    ) {
        this.shoeVariantAssetRepository = shoeVariantAssetRepository;
        this.shoeVariantRepository = shoeVariantRepository;
        this.storageService = storageService;
        this.uuidGenerator = uuidGenerator;
    }

    public void createBatch(
            BrandId associatedBrandId,
            ShoeVariantId shoeVariantId,
            List<MediaFile> assets
    ) {
        ShoeVariant shoeVariant = this.shoeVariantRepository.findById(shoeVariantId)
                .orElseThrow(() -> new ShoeVariantNotExist(shoeVariantId));

        if (!shoeVariant.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(associatedBrandId);

        long totalShoeVariantAssets = shoeVariantAssetRepository.countByShoeVariantId(shoeVariantId);

        if (totalShoeVariantAssets + assets.size() > ShoeVariantAssetPosition.TOTAL_MAXIMUM)
            throw new ExceedsTotalAllowableShoeVariantAssets(shoeVariantId);

        List<CompletableFuture<ShoeVariantAsset>> savingOfShoeVariantAssets = new ArrayList<>();

        for (int index = 1; index <= assets.size(); index++) {
            final int currentIndex = index;
            MediaFile asset = assets.get(index - 1);

            CompletableFuture<ShoeVariantAsset> savingShoeVariantAsset = CompletableFuture.supplyAsync(() -> {
                String shoeVariantAssetBlobUrl;

                try {
                    shoeVariantAssetBlobUrl = storageService.uploadShoeVariantAsset(asset).get().url();
                } catch (InterruptedException | ExecutionException e) {
                    throw new FileUploadFailure(String.format(
                            "A failure occurred while uploading file number <%d>",
                            currentIndex
                    ));
                }

                return this.shoeVariantAssetRepository.save(
                        new ShoeVariantAsset(
                                new ShoeVariantAssetId(uuidGenerator.generate()),
                                shoeVariantId,
                                new ShoeVariantAssetUrl(shoeVariantAssetBlobUrl),
                                new ShoeVariantAssetPosition(currentIndex)
                        )
                );
            });

            savingOfShoeVariantAssets.add(savingShoeVariantAsset);
        }

        try {
            CompletableFuture.allOf(savingOfShoeVariantAssets.toArray(new CompletableFuture[0])).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new FileUploadFailure(e.getMessage());
        }
    }
}
