package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.create;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

import java.math.BigDecimal;

public record CreateShoeVariantCommand(
        String id,
        String associatedBrandId,
        String shoeModelId,
        String name,
        BigDecimal price
) implements Command {
}
