package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.create;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

public record CreateShoeVariantAssetCommand(
        String id,
        String shoeVariantId,
        int position,
        MediaFile asset,
        String associatedBrandId
) implements Command {
}
