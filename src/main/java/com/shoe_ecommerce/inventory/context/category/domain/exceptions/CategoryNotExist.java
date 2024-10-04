package com.shoe_ecommerce.inventory.context.category.domain.exceptions;

import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;
import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class CategoryNotExist extends DomainError {

    public CategoryNotExist(CategoryId id) {
        super("category_not_exist", String.format("The category <%s> doesn't exist", id.value()));
    }
}
