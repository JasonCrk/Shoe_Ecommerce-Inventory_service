package com.shoe_ecommerce.inventory.shared.domain.value_objects;

import java.time.LocalDate;
import java.util.Objects;

public abstract class DateValueObject {
    private final LocalDate value;

    public DateValueObject(LocalDate value) {
        this.value = value;
    }

    public LocalDate value() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        DateValueObject that = (DateValueObject) object;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
