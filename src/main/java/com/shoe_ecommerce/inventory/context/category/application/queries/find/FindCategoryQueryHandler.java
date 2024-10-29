package com.shoe_ecommerce.inventory.context.category.application.queries.find;

import com.shoe_ecommerce.inventory.context.category.application.queries.CategoryResponse;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryHandler;

@Service
public final class FindCategoryQueryHandler implements QueryHandler<FindCategoryQuery, CategoryResponse> {

    private final CategoryFinder finder;

    public FindCategoryQueryHandler(CategoryFinder finder) {
        this.finder = finder;
    }

    @Override
    public CategoryResponse handle(FindCategoryQuery query) {
        return finder.find(new CategoryId(query.id()));
    }
}
