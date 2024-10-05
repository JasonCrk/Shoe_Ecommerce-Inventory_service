package com.shoe_ecommerce.inventory.shared.presentation.validations.max_file_size;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public final class MaxFileSizeValidator implements ConstraintValidator<MaxFileSize, MultipartFile> {

    private static final long KILOBYTE = 1048576L;

    private int maxMegabytes;

    @Override
    public void initialize(MaxFileSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.maxMegabytes = constraintAnnotation.maxMegabytes();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) return true;

        if (multipartFile.getSize() > maxMegabytes * KILOBYTE) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Must be " + maxMegabytes + "MB or less")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
