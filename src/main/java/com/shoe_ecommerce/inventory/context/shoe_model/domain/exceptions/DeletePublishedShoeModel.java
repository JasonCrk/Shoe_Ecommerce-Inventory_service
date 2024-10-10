package com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.shared.domain.exceptions.DomainError;

public class DeletePublishedShoeModel extends DomainError {
    public DeletePublishedShoeModel(ShoeModelId id) {
        super("delete_published_shoe_model", String.format(
                "Its not possible to delete the shoe model <%s> if has been published",
                id.value()
        ));
    }
}
