package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class DeleteShoeInventoryCommandHandler implements CommandHandler<DeleteShoeInventoryCommand, Void> {

    private final ShoeInventoryRemover remover;

    public DeleteShoeInventoryCommandHandler(ShoeInventoryRemover remover) {
        this.remover = remover;
    }

    @Override
    public Void handle(DeleteShoeInventoryCommand command) {
        this.remover.remove(
                new ShoeInventoryId(command.id()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
