package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.create;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelDescription;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelName;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateShoeModelCommandHandler implements CommandHandler<CreateShoeModelCommand, Void> {

    private final ShoeModelCreator shoeModelCreator;

    public CreateShoeModelCommandHandler(ShoeModelCreator shoeModelCreator) {
        this.shoeModelCreator = shoeModelCreator;
    }

    @Override
    public Void handle(CreateShoeModelCommand command) {
        this.shoeModelCreator.create(
                new ShoeModelId(command.id()),
                new ShoeModelName(command.name()),
                new ShoeModelDescription(command.description()),
                new CategoryId(command.categoryId()),
                new BrandId(command.assignedBrandId())
        );

        return null;
    }
}
