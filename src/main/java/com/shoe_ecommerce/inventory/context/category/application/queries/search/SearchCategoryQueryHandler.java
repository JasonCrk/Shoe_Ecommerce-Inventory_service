package com.shoe_ecommerce.inventory.context.category.application.queries.search;

import com.shoe_ecommerce.inventory.context.category.application.queries.CategoryListResponse;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchCategoryQueryHandler implements QueryHandler<SearchCategoriesQuery, CategoryListResponse> {

    private final CategorySearcher searcher;

    public SearchCategoryQueryHandler(CategorySearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public CategoryListResponse handle(SearchCategoriesQuery query) {
        return searcher.search();
    }
}
