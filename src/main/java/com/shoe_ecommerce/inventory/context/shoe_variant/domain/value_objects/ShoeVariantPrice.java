package com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects;

import com.shoe_ecommerce.inventory.shared.domain.value_objects.DecimalValueObject;

import java.math.BigDecimal;

public final class ShoeVariantPrice extends DecimalValueObject {

    public ShoeVariantPrice(BigDecimal value) {
        super(value);
    }
}
