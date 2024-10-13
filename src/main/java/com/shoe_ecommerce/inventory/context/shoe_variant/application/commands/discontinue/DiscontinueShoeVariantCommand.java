package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.discontinue;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record DiscontinueShoeVariantCommand(String shoeVariantId, String associatedBrandId) implements Command {
}
