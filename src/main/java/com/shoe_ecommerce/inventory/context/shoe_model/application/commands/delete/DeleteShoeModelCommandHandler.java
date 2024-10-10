package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class DeleteShoeModelCommandHandler implements CommandHandler<DeleteShoeModelCommand, Void> {

    private final ShoeModelRemover remover;

    public DeleteShoeModelCommandHandler(ShoeModelRemover remover) {
        this.remover = remover;
    }

    @Override
    public Void handle(DeleteShoeModelCommand command) {
        this.remover.remove(
                new ShoeModelId(command.shoeModelId()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
