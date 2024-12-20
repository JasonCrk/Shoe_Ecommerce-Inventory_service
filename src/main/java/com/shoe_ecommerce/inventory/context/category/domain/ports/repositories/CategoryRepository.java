package com.shoe_ecommerce.inventory.context.category.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.category.domain.Category;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(CategoryId id);

    List<Category> findAll();

    boolean existsById(CategoryId id);
}
