package com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.mappers;

import com.shoe_ecommerce.inventory.context.category.domain.Category;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryName;
import com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.models.JpaCategory;

public final class CategoryMapper {

    public static Category toEntity(JpaCategory category) {
        return new Category(
                new CategoryId(category.getId().toString()),
                new CategoryName(category.getName()),
                category.getGender()
        );
    }

    public static JpaCategory toModel(Category category) {
        return new JpaCategory(
                category.id().uuid(),
                category.name().value(),
                category.gender()
        );
    }
}
