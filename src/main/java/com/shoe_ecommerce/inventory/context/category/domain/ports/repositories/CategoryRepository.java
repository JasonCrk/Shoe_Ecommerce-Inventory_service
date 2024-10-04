package com.shoe_ecommerce.inventory.context.category.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

public interface CategoryRepository {
    boolean existsById(CategoryId id);
}
