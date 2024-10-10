package com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeModelIsNotPublished extends DomainError {
    public ShoeModelIsNotPublished(ShoeModelId id) {
        super("shoe_model_is_not_published", String.format("The shoe model <%s> is not published", id.value()));
    }
}
