package com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.find;

import com.shoe_ecommerce.inventory.shared.domain.bus.query.Query;

public record FindShoeInventoryQuery(String id) implements Query {
}
