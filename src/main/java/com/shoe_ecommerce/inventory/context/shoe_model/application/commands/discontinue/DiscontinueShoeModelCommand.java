package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.discontinue;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record DiscontinueShoeModelCommand(String shoeModelId, String associatedBrandId) implements Command {
}
