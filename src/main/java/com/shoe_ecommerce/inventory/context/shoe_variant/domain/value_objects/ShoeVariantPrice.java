package com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects;

import com.shoe_ecommerce.inventory.shared.domain.value_objects.MoneyValueObject;

import java.math.BigDecimal;

public final class ShoeVariantPrice extends MoneyValueObject {

    public ShoeVariantPrice(BigDecimal value) {
        super(value);
    }
}
