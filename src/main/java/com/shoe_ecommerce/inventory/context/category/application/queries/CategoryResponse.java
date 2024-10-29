package com.shoe_ecommerce.inventory.context.category.application.queries;

import com.shoe_ecommerce.inventory.context.category.domain.enums.Gender;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.Response;

public record CategoryResponse(String id, String name, Gender gender) implements Response {
}
