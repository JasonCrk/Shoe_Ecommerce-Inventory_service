package com.shoe_ecommerce.inventory.shared.presentation.validations.video_max_size;

import com.shoe_ecommerce.inventory.shared.presentation.validations.asset_content_type.AllowedAssetContentType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public final class VideoMaxSizeValidator implements ConstraintValidator<VideoMaxSize, MultipartFile> {

    private static final long KILOBYTE = 1048576L;

    private int maxMegabytes;

    @Override
    public void initialize(VideoMaxSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        maxMegabytes = constraintAnnotation.maxMegabytes();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) return true;

        return !multipartFile.getContentType().equals(AllowedAssetContentType.MP4.getMimeType()) &&
                multipartFile.getSize() <= maxMegabytes * KILOBYTE;
    }
}
