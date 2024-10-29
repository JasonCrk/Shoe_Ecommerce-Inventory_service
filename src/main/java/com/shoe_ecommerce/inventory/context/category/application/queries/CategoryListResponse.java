package com.shoe_ecommerce.inventory.context.category.application.queries;

import com.shoe_ecommerce.inventory.shared.domain.bus.query.Response;

import java.util.List;

public record CategoryListResponse(List<CategoryResponse> categories) implements Response {
}
