package com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateShoeInventoryStockRequest(
        @Positive(message = "Must be positive")
        @NotNull(message = "Is required")
        int newStock
) {
}
