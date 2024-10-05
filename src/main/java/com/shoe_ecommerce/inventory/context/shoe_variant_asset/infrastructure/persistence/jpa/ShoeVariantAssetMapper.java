package com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa;

import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAsset;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetPosition;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetUrl;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

public final class ShoeVariantAssetMapper {

    public static ShoeVariantAsset toEntity(JpaShoeVariantAsset asset) {
        return new ShoeVariantAsset(
                new ShoeVariantAssetId(asset.getId().toString()),
                new ShoeVariantId(asset.getShoeVariantId().toString()),
                new ShoeVariantAssetUrl(asset.getUrl()),
                new ShoeVariantAssetPosition(asset.getPosition())
        );
    }

    public static JpaShoeVariantAsset toModel(ShoeVariantAsset asset) {
        return new JpaShoeVariantAsset(
                asset.id().uuid(),
                asset.shoeVariantId().uuid(),
                asset.url().value(),
                asset.position().value()
        );
    }
}
