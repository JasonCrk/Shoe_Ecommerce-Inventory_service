package com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries;

import com.shoe_ecommerce.inventory.shared.domain.bus.query.Response;

import java.math.BigDecimal;

public record ShoeInventoryResponse(
        String id,
        int stock,
        ShoeInventoryResponse.ShoeVariantResponse variant
) implements Response {

    public record ShoeVariantResponse(
            String id,
            String name,
            BigDecimal price,
            boolean isDiscontinued,
            String shoeModelId
    ) {}
}
