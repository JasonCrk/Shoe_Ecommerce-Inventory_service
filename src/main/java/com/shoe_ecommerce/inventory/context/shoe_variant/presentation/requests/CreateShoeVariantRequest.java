package com.shoe_ecommerce.inventory.context.shoe_variant.presentation.requests;

import com.shoe_ecommerce.inventory.context.shared.presentation.validations.is_uuid.IsUuid;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateShoeVariantRequest(
        @NotBlank(message = "Is required")
        @Size(max = 200, message = "Maximum 200 characters")
        String name,

        @NotNull(message = "Is required")
        @Positive(message = "It cannot be negative")
        BigDecimal price,

        @IsUuid(message = "ID is invalid")
        String shoeModelId
) {
}
