package com.shoe_ecommerce.inventory.shared.presentation.validations.asset_content_type;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AssetContentTypeValidator.class})
public @interface AssetContentType {
    AllowedAssetContentType[] contentTypes();
    String message() default "Invalid image format";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
