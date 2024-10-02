package com.shoe_ecommerce.inventory.context.shoe_inventory.domain;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryStock;
import com.shoe_ecommerce.inventory.context.shoe_size.domain.ShoeSizeId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

public final class ShoeInventory {
    private final ShoeInventoryId id;
    private final ShoeSizeId sizeId;
    private final ShoeVariantId variantId;
    private final ShoeInventoryStock stock;

    public ShoeInventory(ShoeInventoryId id, ShoeSizeId sizeId, ShoeVariantId variantId, ShoeInventoryStock stock) {
        this.id = id;
        this.sizeId = sizeId;
        this.variantId = variantId;
        this.stock = stock;
    }

    public static ShoeInventory create(ShoeInventoryId id, ShoeSizeId sizeId, ShoeVariantId variantId, ShoeInventoryStock stock) {
        ShoeInventory shoeInventory = new ShoeInventory(id, sizeId, variantId, stock);
        return shoeInventory;
    }

    public ShoeInventoryId id() {
        return id;
    }

    public ShoeSizeId sizeId() {
        return sizeId;
    }

    public ShoeVariantId variantId() {
        return variantId;
    }

    public ShoeInventoryStock stock() {
        return stock;
    }
}
