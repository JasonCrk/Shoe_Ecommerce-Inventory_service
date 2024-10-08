package com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

public interface ShoeVariantAssetRepository {
    ShoeVariantAsset save(ShoeVariantAsset shoeVariantAsset);
    long countByShoeVariantId(ShoeVariantId shoeVariantId);
}
