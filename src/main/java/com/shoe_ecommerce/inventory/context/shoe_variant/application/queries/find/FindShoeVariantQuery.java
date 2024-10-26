package com.shoe_ecommerce.inventory.context.shoe_variant.application.queries.find;

import com.shoe_ecommerce.inventory.shared.domain.bus.query.Query;

public record FindShoeVariantQuery(String id) implements Query {
}
