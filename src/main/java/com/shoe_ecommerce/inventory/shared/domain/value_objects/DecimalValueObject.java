package com.shoe_ecommerce.inventory.shared.domain.value_objects;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class DecimalValueObject {
    private final BigDecimal value;

    public DecimalValueObject(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal value() {
        return this.value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        DecimalValueObject that = (DecimalValueObject) object;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
