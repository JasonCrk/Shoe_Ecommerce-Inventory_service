package com.shoe_ecommerce.inventory.context.shoe_size.domain;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

public final class ShoeSize {
    private final ShoeSizeId id;
    private final ShoeModelId shoeModelId;
    private final Size size;

    public ShoeSize(ShoeSizeId id, ShoeModelId shoeModelId, Size size) {
        this.id = id;
        this.shoeModelId = shoeModelId;
        this.size = size;
    }

    public ShoeSizeId id() {
        return id;
    }

    public ShoeModelId shoeModelId() {
        return shoeModelId;
    }

    public Size size() {
        return size;
    }
}
