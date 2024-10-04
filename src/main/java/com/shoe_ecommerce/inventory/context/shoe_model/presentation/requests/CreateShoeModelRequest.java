package com.shoe_ecommerce.inventory.context.shoe_model.presentation.requests;

import com.shoe_ecommerce.inventory.context.shared.presentation.validations.is_uuid.IsUuid;

import jakarta.validation.constraints.*;

public record CreateShoeModelRequest(
        @NotBlank(message = "Is required")
        @Size(max = 200, message = "Maximum 200 characters")
        String name,

        @IsUuid(message = "The category ID is invalid")
        @NotBlank(message = "Is required")
        String categoryId,

        @NotBlank(message = "Is required")
        @Size(max = 65535, message = "Maximum 65535 characters")
        String description
) {
}
