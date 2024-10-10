package com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeModelAlreadyDiscontinued extends DomainError {
    public ShoeModelAlreadyDiscontinued(ShoeModelId id) {
        super("shoe_model_already_discontinued", String.format(
                "The shoe model <%s> is already discontinued",
                id.value())
        );
    }
}
