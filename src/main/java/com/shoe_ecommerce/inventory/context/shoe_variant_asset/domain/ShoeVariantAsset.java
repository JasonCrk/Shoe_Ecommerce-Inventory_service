package com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain;

import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetPosition;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetUrl;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import java.util.Objects;

public final class ShoeVariantAsset {
    private final ShoeVariantAssetId id;
    private final ShoeVariantId shoeVariantId;
    private final ShoeVariantAssetUrl url;
    private final ShoeVariantAssetPosition position;

    public ShoeVariantAsset(
            ShoeVariantAssetId id,
            ShoeVariantId shoeVariantId,
            ShoeVariantAssetUrl url,
            ShoeVariantAssetPosition position
    ) {
        this.id = id;
        this.shoeVariantId = shoeVariantId;
        this.url = url;
        this.position = position;
    }

    public ShoeVariantAssetId id() {
        return id;
    }

    public ShoeVariantId shoeVariantId() {
        return shoeVariantId;
    }

    public ShoeVariantAssetUrl url() {
        return url;
    }

    public ShoeVariantAssetPosition position() {
        return position;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        ShoeVariantAsset that = (ShoeVariantAsset) object;
        return id.equals(that.id) &&
                shoeVariantId.equals(that.shoeVariantId) &&
                url.equals(that.url) &&
                position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shoeVariantId, url, position);
    }
}
