package com.shoe_ecommerce.inventory.context.shoe_variant.domain;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantName;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantPrice;

import java.util.Objects;

public final class ShoeVariant {
    private final ShoeVariantId id;
    private final BrandId brandId;
    private final ShoeModelId modelId;
    private final ShoeVariantName name;
    private final ShoeVariantPrice price;

    public ShoeVariant(
            ShoeVariantId id,
            BrandId brandId,
            ShoeModelId modelId,
            ShoeVariantName name,
            ShoeVariantPrice price
    ) {
        this.id = id;
        this.brandId = brandId;
        this.modelId = modelId;
        this.name = name;
        this.price = price;
    }

    public static ShoeVariant create(
            ShoeVariantId id,
            BrandId brandId,
            ShoeModelId modelId,
            ShoeVariantName name,
            ShoeVariantPrice price
    ) {
        ShoeVariant variant = new ShoeVariant(id, brandId, modelId, name, price);
        return variant;
    }

    public ShoeVariantId id() {
        return id;
    }

    public BrandId brandId() {
        return brandId;
    }

    public ShoeModelId modelId() {
        return modelId;
    }

    public ShoeVariantName name() {
        return name;
    }

    public ShoeVariantPrice price() {
        return price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        ShoeVariant that = (ShoeVariant) object;
        return id.equals(that.id) &&
                brandId.equals(that.brandId) &&
                modelId.equals(that.modelId) &&
                name.equals(that.name) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, modelId, name, price);
    }
}
