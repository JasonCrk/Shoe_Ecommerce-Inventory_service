package com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects;

import com.shoe_ecommerce.inventory.shared.domain.value_objects.IntValueObject;

public final class ShoeInventoryStock extends IntValueObject {

    public ShoeInventoryStock(int value) {
        super(ensureIsValid(value));
    }

    private static int ensureIsValid(int value) throws IllegalArgumentException {
        if (value < 0) throw new IllegalArgumentException("Stock cannot be negative");

        return value;
    }
}
