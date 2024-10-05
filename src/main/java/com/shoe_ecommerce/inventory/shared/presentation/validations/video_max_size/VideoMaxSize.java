package com.shoe_ecommerce.inventory.shared.presentation.validations.video_max_size;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = VideoMaxSizeValidator.class)
public @interface VideoMaxSize {
    int maxMegabytes();

    String message() default "File size must be smaller";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
