package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.delete;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record DeleteShoeModelCommand(String shoeModelId, String associatedBrandId) implements Command {
}
