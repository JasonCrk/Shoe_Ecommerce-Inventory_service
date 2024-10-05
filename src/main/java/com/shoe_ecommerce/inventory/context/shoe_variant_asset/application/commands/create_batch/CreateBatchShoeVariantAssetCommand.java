package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.create_batch;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.Command;

import java.util.List;

public record CreateBatchShoeVariantAssetCommand(
        String associatedBrandId,
        String shoeVariantId,
        List<MediaFile> assets
) implements Command {
}
