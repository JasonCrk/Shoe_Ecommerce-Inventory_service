package com.shoe_ecommerce.inventory.context.category.application.queries.find;

import com.shoe_ecommerce.inventory.context.category.application.queries.CategoryResponse;
import com.shoe_ecommerce.inventory.context.category.domain.Category;
import com.shoe_ecommerce.inventory.context.category.domain.exceptions.CategoryNotExist;
import com.shoe_ecommerce.inventory.context.category.domain.ports.repositories.CategoryRepository;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class CategoryFinder {

    private final CategoryRepository categoryRepository;

    public CategoryFinder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse find(CategoryId categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotExist(categoryId));

        return new CategoryResponse(
                category.id().value(),
                category.name().value(),
                category.gender()
        );
    }
}
