package com.shoe_ecommerce.inventory.context.shared.presentation.validations.is_uuid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public final class IsUuidValidator implements ConstraintValidator<IsUuid, String> {

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext constraintValidatorContext) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
