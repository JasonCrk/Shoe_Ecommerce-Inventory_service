package com.shoe_ecommerce.inventory.shared.presentation.validations.max_file_size;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MaxFileSizeValidator.class)
public @interface MaxFileSize {
    int maxMegabytes();

    String message() default "File size must be smaller";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
