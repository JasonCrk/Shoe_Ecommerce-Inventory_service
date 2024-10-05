package com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions;

import com.shoe_ecommerce.inventory.context.category.domain.enums.Gender;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class SizeNotCorrespondingToGender extends DomainError {
    public SizeNotCorrespondingToGender(double size, Gender gender) {
        super(
                "size_not_corresponding_to_gender",
                String.format(
                        "The size %.0f does not correspond to the %s gender of the shoe model",
                        size, gender.toString().toLowerCase()
                )
        );
    }
}
