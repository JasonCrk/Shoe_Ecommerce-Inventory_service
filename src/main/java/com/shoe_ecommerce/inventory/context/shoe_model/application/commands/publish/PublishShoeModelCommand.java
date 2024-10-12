package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.publish;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record PublishShoeModelCommand(String shoeModelId, String associatedBrandId) implements Command {
}
