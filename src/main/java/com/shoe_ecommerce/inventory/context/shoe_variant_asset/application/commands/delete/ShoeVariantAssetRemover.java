package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAsset;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAssetRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions.RemoveOnlyAssetOfShoeVariantAfterPublication;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions.ShoeVariantAssetNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeVariantAssetRemover {

    private final ShoeVariantAssetRepository shoeVariantAssetRepository;
    private final ShoeVariantRepository shoeVariantRepository;
    private final ShoeModelRepository shoeModelRepository;

    public ShoeVariantAssetRemover(
            ShoeVariantAssetRepository shoeVariantAssetRepository,
            ShoeVariantRepository shoeVariantRepository,
            ShoeModelRepository shoeModelRepository
    ) {
        this.shoeVariantAssetRepository = shoeVariantAssetRepository;
        this.shoeVariantRepository = shoeVariantRepository;
        this.shoeModelRepository = shoeModelRepository;
    }

    public void remove(ShoeVariantAssetId id, BrandId associatedBrandId) {
        ShoeVariantAsset shoeVariantAsset = shoeVariantAssetRepository.findById(id)
                .orElseThrow(() -> new ShoeVariantAssetNotExist(id));

        ShoeVariant shoeVariant = shoeVariantRepository.findById(shoeVariantAsset.shoeVariantId())
                .orElseThrow(() -> new ShoeVariantNotExist(shoeVariantAsset.shoeVariantId()));

        if (!shoeVariant.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeVariant.brandId());

        long totalShoeVariantAssets = shoeVariantAssetRepository.countByShoeVariantId(shoeVariant.id());

        ShoeModel shoeModel = shoeModelRepository.findById(shoeVariant.modelId())
                .orElseThrow(() -> new ShoeModelNotExist(shoeVariant.modelId()));

        if (shoeModel.isPublished().value() && totalShoeVariantAssets == 1)
            throw new RemoveOnlyAssetOfShoeVariantAfterPublication(shoeVariant.id());

        shoeVariantAssetRepository.deleteById(id);
        shoeVariantAssetRepository.reduceByOneThePositionByShoeVariantAssetPosition(
                shoeVariant.id(),
                shoeVariantAsset.position()
        );
    }
}
