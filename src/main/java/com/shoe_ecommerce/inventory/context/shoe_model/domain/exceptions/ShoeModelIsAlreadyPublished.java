package com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeModelIsAlreadyPublished extends DomainError {
    public ShoeModelIsAlreadyPublished(ShoeModelId id) {
        super("shoe_model_is_already_published", String.format(
                "The shoe model <%s> is already published",
                id.value()
        ));
    }
}
