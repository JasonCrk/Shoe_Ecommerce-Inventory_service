package com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import java.util.Optional;

public interface ShoeInventoryRepository {
    Optional<ShoeInventory> findById(ShoeInventoryId id);
    Optional<BrandId> getBrandIdById(ShoeInventoryId id);

    boolean existsByShoeModelId(ShoeModelId shoeModelId);

    ShoeInventory save(ShoeInventory shoeInventory);

    void deleteById(ShoeInventoryId id);
}
