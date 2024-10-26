package com.shoe_ecommerce.inventory.context.shoe_variant.application.queries.find;

import com.shoe_ecommerce.inventory.shared.domain.bus.query.Response;

import java.math.BigDecimal;

public record ShoeVariantResponse(
        String id,
        String brandId,
        String modelId,
        String name,
        BigDecimal price,
        boolean isDiscontinued
) implements Response {
}
