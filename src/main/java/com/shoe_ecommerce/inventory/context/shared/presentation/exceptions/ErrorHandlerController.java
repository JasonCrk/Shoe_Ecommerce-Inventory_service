package com.shoe_ecommerce.inventory.context.shared.presentation.exceptions;

import com.shoe_ecommerce.inventory.context.category.domain.exceptions.CategoryNotExist;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions.ShoeInventoryNotExist;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions.SizeNotCorrespondingToGender;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.*;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.DeletePublishedShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantAlreadyDiscontinued;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.SomeShoeVariantsHaveNotAssetsAtAll;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions.ExceedsTotalAllowableShoeVariantAssets;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions.RemoveOnlyAssetOfShoeVariantAfterPublication;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.exceptions.ShoeVariantAssetNotExist;

import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.BrandNotExist;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.FileUploadFailure;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(CategoryNotExist.class)
    public ResponseEntity<DomainErrorResponse> handleCategoryNotExist(CategoryNotExist ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BrandNotExist.class)
    public ResponseEntity<DomainErrorResponse> handleBrandNotExist(BrandNotExist ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShoeInventoryNotExist.class)
    public ResponseEntity<DomainErrorResponse> handleShoeInventoryNotExist(ShoeInventoryNotExist ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SizeNotCorrespondingToGender.class)
    public ResponseEntity<DomainErrorResponse> handleSizeNotCorrespondingToGender(SizeNotCorrespondingToGender ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeletePublishedShoeModel.class)
    public ResponseEntity<DomainErrorResponse> handleDeletePublishedShoeModel(DeletePublishedShoeModel ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoeModelAlreadyDiscontinued.class)
    public ResponseEntity<DomainErrorResponse> handleShoeModelAlreadyDiscontinued(ShoeModelAlreadyDiscontinued ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoeModelIsAlreadyPublished.class)
    public ResponseEntity<DomainErrorResponse> handleShoeModelIsAlreadyPublished(ShoeModelIsAlreadyPublished ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoeModelIsNotPublished.class)
    public ResponseEntity<DomainErrorResponse> handleShoeModelIsNotPublished(ShoeModelIsNotPublished ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoeModelNotExist.class)
    public ResponseEntity<DomainErrorResponse> handleShoeModelNotExist(ShoeModelNotExist ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShoeModelNotReadyForPublication.class)
    public ResponseEntity<DomainErrorResponse> handleShoeModelNotReadyForPublication(
            ShoeModelNotReadyForPublication ex
    ) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeletePublishedShoeVariant.class)
    public ResponseEntity<DomainErrorResponse> handleDeletePublishedShoeVariant(DeletePublishedShoeVariant ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoeVariantAlreadyDiscontinued.class)
    public ResponseEntity<DomainErrorResponse> handleShoeVariantAlreadyDiscontinued(ShoeVariantAlreadyDiscontinued ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoeVariantNotExist.class)
    public ResponseEntity<DomainErrorResponse> handleShoeVariantNotExist(ShoeVariantNotExist ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceedsTotalAllowableShoeVariantAssets.class)
    public ResponseEntity<DomainErrorResponse> handleExceedsTotalAllowableShoeVariantAssets(
            ExceedsTotalAllowableShoeVariantAssets ex
    ) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RemoveOnlyAssetOfShoeVariantAfterPublication.class)
    public ResponseEntity<DomainErrorResponse> handleRemoveOnlyAssetOfShoeVariantAfterPublication(
            RemoveOnlyAssetOfShoeVariantAfterPublication ex
    ) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoeVariantAssetNotExist.class)
    public ResponseEntity<DomainErrorResponse> handleShoeVariantAssetNotExist(ShoeVariantAssetNotExist ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileUploadFailure.class)
    public ResponseEntity<DomainErrorResponse> handleFileUploadFailure(FileUploadFailure ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAssociatedBrand.class)
    public ResponseEntity<DomainErrorResponse> handleUnauthorizedAssociatedBrand(UnauthorizedAssociatedBrand ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SomeShoeVariantsHaveNotAssetsAtAll.class)
    public ResponseEntity<DomainErrorResponse> handleSomeShoeVariantsHaveNotAssetsAtAll(
            SomeShoeVariantsHaveNotAssetsAtAll ex
    ) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorValidationResponse> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        List<String> globalErrors = new ArrayList<>();

        ex.getGlobalErrors().forEach(error -> globalErrors.add(error.getDefaultMessage()));

        return new ResponseEntity<>(
                new ErrorValidationResponse(globalErrors, fieldErrors),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<DomainErrorResponse> handleException(DomainError domainError, HttpStatus status) {
        return ResponseEntity
                .status(status.value())
                .body(new DomainErrorResponse(status.value(), domainError.errorCode(), domainError.errorMessage()));
    }
}
