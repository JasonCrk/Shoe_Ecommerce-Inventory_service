package com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeModelNotExist extends DomainError {
    public ShoeModelNotExist(ShoeModelId id) {
        super("shoe_model_not_exist", String.format("The shoe model <%s> doesn't exist", id.value()));
    }
}
