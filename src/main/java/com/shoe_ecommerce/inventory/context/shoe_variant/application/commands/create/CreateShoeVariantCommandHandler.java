package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.create;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantName;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantPrice;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateShoeVariantCommandHandler implements CommandHandler<CreateShoeVariantCommand, Void> {

    private final ShoeVariantCreator creator;

    public CreateShoeVariantCommandHandler(ShoeVariantCreator creator) {
        this.creator = creator;
    }

    @Override
    public Void handle(CreateShoeVariantCommand command) {
        this.creator.create(
                new ShoeVariantId(command.id()),
                new ShoeModelId(command.shoeModelId()),
                new ShoeVariantName(command.name()),
                new ShoeVariantPrice(command.price()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
