package com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.find;

import com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.ShoeInventoryResponse;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryHandler;

@Service
public final class FindShoeInventoryQueryHandler implements QueryHandler<FindShoeInventoryQuery, ShoeInventoryResponse> {
    private final ShoeInventoryFinder finder;

    public FindShoeInventoryQueryHandler(ShoeInventoryFinder finder) {
        this.finder = finder;
    }

    @Override
    public ShoeInventoryResponse handle(FindShoeInventoryQuery query) {
        return this.finder.find(new ShoeInventoryId(query.id()));
    }
}
