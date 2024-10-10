package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class DeleteShoeVariantCommandHandler implements CommandHandler<DeleteShoeVariantCommand, Void> {

    private final ShoeVariantRemover remover;

    public DeleteShoeVariantCommandHandler(ShoeVariantRemover remover) {
        this.remover = remover;
    }

    @Override
    public Void handle(DeleteShoeVariantCommand command) {
        this.remover.remove(
                new ShoeVariantId(command.shoeVariantId()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
