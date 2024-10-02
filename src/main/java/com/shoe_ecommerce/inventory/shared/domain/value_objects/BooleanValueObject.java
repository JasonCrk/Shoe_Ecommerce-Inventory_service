package com.shoe_ecommerce.inventory.shared.domain.value_objects;

import java.util.Objects;

public abstract class BooleanValueObject {
    private final boolean value;

    public BooleanValueObject(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return this.value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        BooleanValueObject that = (BooleanValueObject) object;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
