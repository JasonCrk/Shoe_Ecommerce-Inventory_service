package com.shoe_ecommerce.inventory.context.shoe_model.presentation.requests;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateShoeVariantRequest(
        @NotBlank(message = "Is required")
        @Size(max = 200, message = "Maximum 200 characters")
        String name,

        @NotNull(message = "Is required")
        @Positive(message = "It cannot be negative")
        BigDecimal price
) {
}
