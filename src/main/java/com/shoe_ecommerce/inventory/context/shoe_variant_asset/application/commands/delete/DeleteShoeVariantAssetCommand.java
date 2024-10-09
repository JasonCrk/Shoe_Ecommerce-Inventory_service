package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.delete;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record DeleteShoeVariantAssetCommand(
        String shoeVariantAssetId,
        String associatedBrandId
) implements Command {
}
