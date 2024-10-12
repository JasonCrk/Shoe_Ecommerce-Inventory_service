package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.publish;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class PublishShoeModelCommandHandler implements CommandHandler<PublishShoeModelCommand, Void> {

    private final ShoeModelPublisher publisher;

    public PublishShoeModelCommandHandler(ShoeModelPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public Void handle(PublishShoeModelCommand command) {
        this.publisher.publish(
                new ShoeModelId(command.shoeModelId()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
