package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class DeleteShoeVariantAssetCommandHandler implements CommandHandler<DeleteShoeVariantAssetCommand, Void> {

    private final ShoeVariantAssetRemover remover;

    public DeleteShoeVariantAssetCommandHandler(ShoeVariantAssetRemover remover) {
        this.remover = remover;
    }

    @Override
    public Void handle(DeleteShoeVariantAssetCommand command) {
        this.remover.remove(
                new ShoeVariantAssetId(command.shoeVariantAssetId()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
