package com.shoe_ecommerce.inventory.shared.presentation.validations.image_max_size;

import com.shoe_ecommerce.inventory.shared.presentation.validations.asset_content_type.AllowedAssetContentType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public final class ImageMaxSizeValidator implements ConstraintValidator<ImageMaxSize, MultipartFile> {

    private static final long KILOBYTE = 1048576L;

    private int maxMegabytes;

    @Override
    public void initialize(ImageMaxSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        maxMegabytes = constraintAnnotation.maxMegabytes();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) return true;

        return !multipartFile.getContentType().equals(AllowedAssetContentType.PNG.getMimeType()) ||
                multipartFile.getSize() <= maxMegabytes * KILOBYTE;
    }
}
