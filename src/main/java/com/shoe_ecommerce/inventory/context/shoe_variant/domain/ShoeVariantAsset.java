package com.shoe_ecommerce.inventory.context.shoe_variant.domain;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantAssetId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantAssetUrl;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

public final class ShoeVariantAsset {
    private final ShoeVariantAssetId id;
    private final ShoeVariantId variantId;
    private final ShoeVariantAssetUrl url;

    public ShoeVariantAsset(ShoeVariantAssetId id, ShoeVariantId variantId, ShoeVariantAssetUrl url) {
        this.id = id;
        this.variantId = variantId;
        this.url = url;
    }

    public ShoeVariantAssetId id() {
        return id;
    }

    public ShoeVariantId variantId() {
        return variantId;
    }

    public ShoeVariantAssetUrl url() {
        return url;
    }
}
