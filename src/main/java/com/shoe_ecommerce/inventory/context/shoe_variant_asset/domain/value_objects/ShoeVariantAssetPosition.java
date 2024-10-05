package com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects;

import com.shoe_ecommerce.inventory.shared.domain.value_objects.IntValueObject;

public final class ShoeVariantAssetPosition extends IntValueObject {

    public ShoeVariantAssetPosition(int value) {
        super(ensureIsValid(value));
    }

    private static int ensureIsValid(int value) throws IllegalArgumentException {
        if (value <= 0) throw new IllegalArgumentException("The position must be positive");

        return value;
    }
}
