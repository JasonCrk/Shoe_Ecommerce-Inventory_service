package com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetPosition;

import java.util.Optional;

public interface ShoeVariantAssetRepository {
    Optional<ShoeVariantAsset> findById(ShoeVariantAssetId id);

    long countByShoeVariantId(ShoeVariantId shoeVariantId);

    ShoeVariantAsset save(ShoeVariantAsset shoeVariantAsset);

    void deleteById(ShoeVariantAssetId id);

    void reduceByOneThePositionByShoeVariantAssetPosition(ShoeVariantId id, ShoeVariantAssetPosition position);
}
