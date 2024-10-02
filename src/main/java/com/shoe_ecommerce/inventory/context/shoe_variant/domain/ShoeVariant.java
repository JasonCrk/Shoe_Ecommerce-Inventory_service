package com.shoe_ecommerce.inventory.context.shoe_variant.domain;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantName;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantPosition;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantPrice;

import java.util.Objects;

public final class ShoeVariant {
    private final ShoeVariantId id;
    private final ShoeModelId modelId;
    private final ShoeVariantName name;
    private final ShoeVariantPrice price;
    private final ShoeVariantPosition position;

    public ShoeVariant(
            ShoeVariantId id,
            ShoeModelId modelId,
            ShoeVariantName name,
            ShoeVariantPrice price,
            ShoeVariantPosition position
    ) {
        this.id = id;
        this.modelId = modelId;
        this.name = name;
        this.price = price;
        this.position = position;
    }

    public static ShoeVariant create(
            ShoeVariantId id,
            ShoeModelId modelId,
            ShoeVariantName name,
            ShoeVariantPrice price,
            ShoeVariantPosition position
    ) {
        ShoeVariant variant = new ShoeVariant(id, modelId, name, price, position);
        return variant;
    }

    public ShoeVariantId id() {
        return id;
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

    public ShoeVariantPosition position() {
        return position;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        ShoeVariant that = (ShoeVariant) object;
        return id.equals(that.id) &&
                modelId.equals(that.modelId) &&
                name.equals(that.name) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelId, name, price);
    }
}
