package com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects;

import com.shoe_ecommerce.inventory.shared.domain.value_objects.DateValueObject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class ShoeModelDatePublished extends DateValueObject {

    public ShoeModelDatePublished(LocalDate value) {
        super(value);
    }

    public ShoeModelDatePublished() {
        super(null);
    }

    public static ShoeModelDatePublished now() {
        return new ShoeModelDatePublished(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate());
    }
}
