package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.create;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record CreateShoeInventoryCommand(
        String associatedBrandId,
        String id,
        String shoeVariantId,
        double size,
        int stock
) implements Command {
}
