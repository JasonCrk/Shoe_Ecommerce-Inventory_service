package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.delete;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record DeleteShoeVariantCommand(
        String shoeVariantId,
        String associatedBrandId
) implements Command {
}
