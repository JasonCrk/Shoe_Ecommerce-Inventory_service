package com.shoe_ecommerce.inventory.shared.presentation.validations.image_max_size;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageMaxSizeValidator.class)
@Documented
public @interface ImageMaxSize {
    int maxMegabytes();
    String message() default "File size must be smaller";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
