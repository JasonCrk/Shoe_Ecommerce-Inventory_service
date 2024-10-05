package com.shoe_ecommerce.inventory.shared.presentation.validations.asset_content_type;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class AssetContentTypeValidator implements ConstraintValidator<AssetContentType, MultipartFile> {

    private AllowedAssetContentType[] contentTypes;

    @Override
    public void initialize(AssetContentType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        contentTypes = constraintAnnotation.contentTypes();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) return true;

        boolean noContentTypeMatches = Arrays.stream(contentTypes)
                .noneMatch(contentType -> contentType.getMimeType().equals(multipartFile.getContentType()));

        if (noContentTypeMatches) {
            String extensions = Arrays.stream(contentTypes)
                    .map(AllowedAssetContentType::getExtension).collect(Collectors.joining(", "));

            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("The type of image must belong to one of the following: " + extensions)
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
