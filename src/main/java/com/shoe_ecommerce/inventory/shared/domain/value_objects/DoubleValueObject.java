package com.shoe_ecommerce.inventory.shared.domain.value_objects;

import java.util.Objects;

public abstract class DoubleValueObject {
    private final double value;

    public DoubleValueObject(double value) {
        this.value = value;
    }

    public double value() {
        return this.value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        DoubleValueObject that = (DoubleValueObject) object;
        return Double.compare(value, that.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
