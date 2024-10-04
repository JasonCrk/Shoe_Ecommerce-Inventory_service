package com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.mappers;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventorySize;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryStock;
import com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.models.JpaShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

public class ShoeInventoryMapper {

    public static ShoeInventory toEntity(JpaShoeInventory inventory) {
        return new ShoeInventory(
                new ShoeInventoryId(inventory.getId().toString()),
                new ShoeInventorySize(inventory.getSize()),
                new ShoeVariantId(inventory.getShoeVariantId().toString()),
                new ShoeInventoryStock(inventory.getStock())
        );
    }

    public static JpaShoeInventory toModel(ShoeInventory inventory) {
        return new JpaShoeInventory(
                inventory.id().uuid(),
                inventory.size().value(),
                inventory.stock().value(),
                inventory.variantId().uuid()
        );
    }
}
