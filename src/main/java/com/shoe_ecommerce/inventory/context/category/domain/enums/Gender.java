package com.shoe_ecommerce.inventory.context.category.domain.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE(39, 48),
    FEMALE(35, 42),
    CHILD(20, 39);

    private final double minSize;
    private final double maxSize;

    Gender(double minSize, double maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }
}
