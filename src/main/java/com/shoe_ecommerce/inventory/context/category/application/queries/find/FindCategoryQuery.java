package com.shoe_ecommerce.inventory.context.category.application.queries.find;

import com.shoe_ecommerce.inventory.shared.domain.bus.query.Query;

public record FindCategoryQuery(String id) implements Query {
}
