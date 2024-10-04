package com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects;

import com.shoe_ecommerce.inventory.shared.domain.value_objects.DoubleValueObject;

public final class ShoeInventorySize extends DoubleValueObject {

    public ShoeInventorySize(double value) {
        super(value);
    }

    public static double ensureIsValid(double value) throws IllegalArgumentException {
        if (value > 0) throw new IllegalArgumentException("Should be positive");

        if (value % 1 != 0) {
            double decimal = value - Math.floor(value);
            if (decimal != 0.5) throw new IllegalArgumentException("Decimal must be only 5");
        }

        return value;
    }
}
