package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.create;

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
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.FileUploadFailure;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;
import com.shoe_ecommerce.inventory.context.shared.domain.ports.services.storage.BlobStorageService;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;
import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

@Service
public final class ShoeVariantAssetCreator {

    private final ShoeVariantRepository shoeVariantRepository;
    private final ShoeVariantAssetRepository shoeVariantAssetRepository;

    private final BlobStorageService storageService;

    Logger logger = LoggerFactory.getLogger(ShoeVariantAssetCreator.class);

    public ShoeVariantAssetCreator(
            ShoeVariantRepository shoeVariantRepository,
            ShoeVariantAssetRepository shoeVariantAssetRepository,
            BlobStorageService storageService
    ) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.shoeVariantAssetRepository = shoeVariantAssetRepository;
        this.storageService = storageService;
    }

    public void create(
            ShoeVariantAssetId id,
            ShoeVariantAssetPosition position,
            ShoeVariantId shoeVariantId,
            MediaFile asset,
            BrandId associatedBrandId
    ) {
        ShoeVariant shoeVariant = shoeVariantRepository.findById(shoeVariantId)
                .orElseThrow(() -> new ShoeVariantNotExist(shoeVariantId));

        if (!shoeVariant.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeVariant.brandId());

        long totalAssets = shoeVariantAssetRepository.countByShoeVariantId(shoeVariantId);

        if (totalAssets == ShoeVariantAssetPosition.TOTAL_MAXIMUM)
            throw new ExceedsTotalAllowableShoeVariantAssets(shoeVariantId);

        ShoeVariantAssetPosition assetPosition = position;

        int nextAssetPosition = (int) totalAssets + 1;

        if (totalAssets < position.value() && nextAssetPosition != position.value())
            assetPosition = new ShoeVariantAssetPosition(nextAssetPosition);

        ShoeVariantAssetUrl assetUrl;

        try {
             String uploadedUrl = storageService.uploadShoeVariantAsset(asset).get().url();
             assetUrl = new ShoeVariantAssetUrl(uploadedUrl);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            throw new FileUploadFailure("An error occurred while trying to upload the file");
        }

        if (nextAssetPosition != assetPosition.value()) {
            shoeVariantAssetRepository.incrementByOneThePositionByShoeVariantIdAndGreaterThanOrEqualPosition(
                    shoeVariantId,
                    assetPosition
            );
        }

        ShoeVariantAsset shoeVariantAsset = ShoeVariantAsset.create(id, shoeVariantId, assetUrl, assetPosition);
        shoeVariantAssetRepository.save(shoeVariantAsset);

        logger.info(
                "The <{}> brand has created new shoe variant asset <{}>", associatedBrandId.value(), id.value()
        );
    }
}
