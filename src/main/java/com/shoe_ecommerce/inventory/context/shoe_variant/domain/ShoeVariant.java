package com.shoe_ecommerce.inventory.context.shoe_variant.domain;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantName;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantPrice;

public final class ShoeVariant {
    private final ShoeVariantId id;
    private final ShoeModelId modelId;
    private final ShoeVariantName name;
    private final ShoeVariantPrice price;

    public ShoeVariant(ShoeVariantId id, ShoeModelId modelId, ShoeVariantName name, ShoeVariantPrice price) {
        this.id = id;
        this.modelId = modelId;
        this.name = name;
        this.price = price;
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
}
