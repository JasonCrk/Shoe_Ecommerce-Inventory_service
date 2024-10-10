package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.discontinue;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class DiscontinueShoeModelCommandHandler implements CommandHandler<DiscontinueShoeModelCommand, Void> {

    private final ShoeModelDiscontinuator discontinuer;

    public DiscontinueShoeModelCommandHandler(ShoeModelDiscontinuator discontinuer) {
        this.discontinuer = discontinuer;
    }

    @Override
    public Void handle(DiscontinueShoeModelCommand command) {
        this.discontinuer.discontinue(
                new ShoeModelId(command.shoeModelId()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
