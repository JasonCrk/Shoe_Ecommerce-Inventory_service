package com.shoe_ecommerce.inventory.context.shoe_inventory.domain;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventorySize;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryStock;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import java.util.Objects;

public final class ShoeInventory {
    private final ShoeInventoryId id;
    private final ShoeInventorySize size;
    private final ShoeVariantId variantId;
    private final ShoeInventoryStock stock;

    public ShoeInventory(
            ShoeInventoryId id,
            ShoeInventorySize size,
            ShoeVariantId variantId,
            ShoeInventoryStock stock
    ) {
        this.id = id;
        this.size = size;
        this.variantId = variantId;
        this.stock = stock;
    }

    public static ShoeInventory create(
            ShoeInventoryId id,
            ShoeInventorySize size,
            ShoeVariantId variantId,
            ShoeInventoryStock stock
    ) {
        ShoeInventory shoeInventory = new ShoeInventory(id, size, variantId, stock);
        return shoeInventory;
    }

    public ShoeInventoryId id() {
        return id;
    }

    public ShoeInventorySize size() {
        return size;
    }

    public ShoeVariantId variantId() {
        return variantId;
    }

    public ShoeInventoryStock stock() {
        return stock;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        ShoeInventory that = (ShoeInventory) object;
        return id.equals(that.id) &&
                size.equals(that.size) &&
                variantId.equals(that.variantId) &&
                stock.equals(that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, variantId, stock);
    }
}
