package com.shoe_ecommerce.inventory.context.shared.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class BrandNotExist extends DomainError {

    public BrandNotExist(BrandId id) {
        super("brand_not_exist", String.format("The brand <%s> doesn't exist", id.value()));
    }
}
