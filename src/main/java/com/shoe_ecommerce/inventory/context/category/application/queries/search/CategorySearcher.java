package com.shoe_ecommerce.inventory.context.category.application.queries.search;

import com.shoe_ecommerce.inventory.context.category.application.queries.CategoryListResponse;
import com.shoe_ecommerce.inventory.context.category.application.queries.CategoryResponse;
import com.shoe_ecommerce.inventory.context.category.domain.ports.repositories.CategoryRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import java.util.List;

@Service
public final class CategorySearcher {

    private final CategoryRepository categoryRepository;

    public CategorySearcher(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryListResponse search() {
        List<CategoryResponse> categories = categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(
                        category.id().value(),
                        category.name().value(),
                        category.gender())
                ).toList();

        return new CategoryListResponse(categories);
    }
}
