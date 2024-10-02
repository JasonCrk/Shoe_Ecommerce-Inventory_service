package com.shoe_ecommerce.inventory.shared.domain.value_objects;

import java.util.Objects;

public abstract class IntValueObject {
    private final int value;

    protected IntValueObject(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        IntValueObject that = (IntValueObject) object;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
