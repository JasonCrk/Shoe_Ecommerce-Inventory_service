package com.shoe_ecommerce.inventory.context.category.domain;

import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryName;

import java.util.Objects;

public final class Category {
    private final CategoryId id;
    private final CategoryName name;

    public Category(CategoryId id, CategoryName name) {
        this.id = id;
        this.name = name;
    }

    public CategoryId id() {
        return id;
    }

    public CategoryName name() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        Category category = (Category) object;
        return id.equals(category.id) && name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
