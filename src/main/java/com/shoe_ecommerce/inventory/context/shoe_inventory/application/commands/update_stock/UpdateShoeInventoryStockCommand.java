package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.update_stock;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record UpdateShoeInventoryStockCommand(
        String shoeInventoryId,
        int newStock,
        String associatedBrandId
) implements Command {
}
