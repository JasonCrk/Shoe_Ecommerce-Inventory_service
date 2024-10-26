package com.shoe_ecommerce.inventory.context.shared.presentation.exceptions;

import java.util.List;
import java.util.Map;

public record ErrorValidationResponse(
        List<String> global,
        Map<String, String> fields
) {
}
