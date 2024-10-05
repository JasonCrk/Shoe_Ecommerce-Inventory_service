package com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeVariantNotExist extends DomainError {
    public ShoeVariantNotExist(ShoeVariantId id) {
        super("shoe_variant_not_exist", String.format("The shoe variant <%s> doesn't exist", id.value()));
    }
}
