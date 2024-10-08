package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.delete;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record DeleteShoeInventoryCommand(String id, String associatedBrandId) implements Command {
}
