package com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.requests;

import com.shoe_ecommerce.inventory.context.shared.presentation.validations.is_uuid.IsUuid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateShoeInventoryRequest(
        @NotBlank(message = "Is required")
        @NotNull(message = "Is required")
        @IsUuid(message = "Is invalid")
        String shoeVariantId,

        @NotNull(message = "Is required")
        @Positive(message = "Must be positive")
        double size,

        @NotNull(message = "Is required")
        @Positive(message = "Must be positive")
        int stock
) {
}
