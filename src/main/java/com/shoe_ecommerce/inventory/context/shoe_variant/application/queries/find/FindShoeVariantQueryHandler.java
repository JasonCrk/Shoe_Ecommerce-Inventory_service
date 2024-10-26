package com.shoe_ecommerce.inventory.context.shoe_variant.application.queries.find;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryHandler;

@Service
public final class FindShoeVariantQueryHandler implements QueryHandler<FindShoeVariantQuery, ShoeVariantResponse> {

    private final ShoeVariantFinder finder;

    public FindShoeVariantQueryHandler(ShoeVariantFinder finder) {
        this.finder = finder;
    }

    @Override
    public ShoeVariantResponse handle(FindShoeVariantQuery query) {
        return finder.find(new ShoeVariantId(query.id()));
    }
}
