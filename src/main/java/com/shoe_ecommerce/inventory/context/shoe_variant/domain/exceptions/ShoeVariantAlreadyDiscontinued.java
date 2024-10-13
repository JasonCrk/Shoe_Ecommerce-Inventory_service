package com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class ShoeVariantAlreadyDiscontinued extends DomainError {
    public ShoeVariantAlreadyDiscontinued(ShoeVariantId id) {
        super("shoe_variant_already_discontinued", String.format(
                "The shoe variant <%s> is already discontinued",
                id.value()
        ));
    }
}
