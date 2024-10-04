package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.create;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record CreateShoeModelCommand(
        String id,
        String name,
        String description,
        String categoryId,
        String assignedBrandId
) implements Command {
}
