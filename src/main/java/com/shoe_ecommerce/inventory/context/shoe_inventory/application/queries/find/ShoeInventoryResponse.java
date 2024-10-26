package com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.find;

import com.shoe_ecommerce.inventory.shared.domain.bus.query.Response;

public record ShoeInventoryResponse(String id, double size, int stock) implements Response {
}
