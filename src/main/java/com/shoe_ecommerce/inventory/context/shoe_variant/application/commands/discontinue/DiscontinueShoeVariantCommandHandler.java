package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.discontinue;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class DiscontinueShoeVariantCommandHandler implements CommandHandler<DiscontinueShoeVariantCommand, Void> {

    private final ShoeVariantDiscontinuator discontinuator;

    public DiscontinueShoeVariantCommandHandler(ShoeVariantDiscontinuator discontinuator) {
        this.discontinuator = discontinuator;
    }

    @Override
    public Void handle(DiscontinueShoeVariantCommand command) {
        this.discontinuator.discontinue(
                new ShoeVariantId(command.shoeVariantId()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
