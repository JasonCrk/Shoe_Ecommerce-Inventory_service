package com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;

public interface ShoeInventoryRepository {
    ShoeInventory save(ShoeInventory shoeInventory);
}
