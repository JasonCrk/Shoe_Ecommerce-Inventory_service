package com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;

import java.util.Optional;

public interface ShoeInventoryRepository {
    Optional<ShoeInventory> findById(ShoeInventoryId id);
    Optional<BrandId> getBrandIdById(ShoeInventoryId id);

    ShoeInventory save(ShoeInventory shoeInventory);

    void deleteById(ShoeInventoryId id);
}
