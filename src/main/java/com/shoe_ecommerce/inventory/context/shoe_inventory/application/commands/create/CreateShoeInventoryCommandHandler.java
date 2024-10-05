package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.create;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventorySize;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryStock;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateShoeInventoryCommandHandler implements CommandHandler<CreateShoeInventoryCommand, Void> {

    private final ShoeInventoryCreator creator;

    public CreateShoeInventoryCommandHandler(ShoeInventoryCreator creator) {
        this.creator = creator;
    }

    @Override
    public Void handle(CreateShoeInventoryCommand command) {
        this.creator.create(
                new ShoeInventoryId(command.id()),
                new ShoeVariantId(command.shoeVariantId()),
                new ShoeInventorySize(command.size()),
                new ShoeInventoryStock(command.stock()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
