package com.shoe_ecommerce.inventory.context.shared.presentation.validations.is_uuid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsUuidValidator.class)
public @interface IsUuid {
    String message() default "Invalid value";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
