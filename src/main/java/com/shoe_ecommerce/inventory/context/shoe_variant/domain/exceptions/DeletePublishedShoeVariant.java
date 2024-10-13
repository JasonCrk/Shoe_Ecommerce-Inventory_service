package com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class DeletePublishedShoeVariant extends DomainError {
    public DeletePublishedShoeVariant(ShoeVariantId id) {
        super("delete_published_shoe_variant", String.format(
                "Its not possible to delete the shoe variant <%s> if has been published",
                id.value()
        ));
    }
}
