package com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeModelNotReadyForPublication extends DomainError {
    public ShoeModelNotReadyForPublication(ShoeModelId id) {
        super("shoe_model_not_ready_for_publication", String.format(
                "The shoe model <%s> is not ready for publication",
                id.value()
        ));
    }
}
