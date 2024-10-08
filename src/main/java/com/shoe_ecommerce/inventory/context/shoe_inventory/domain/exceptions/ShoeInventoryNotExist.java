package com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeInventoryNotExist extends DomainError {
    public ShoeInventoryNotExist(ShoeInventoryId id) {
        super("shoe_inventory_not_exist", String.format("The shoe inventory <%s> doesn't exist", id.value()));
    }
}
