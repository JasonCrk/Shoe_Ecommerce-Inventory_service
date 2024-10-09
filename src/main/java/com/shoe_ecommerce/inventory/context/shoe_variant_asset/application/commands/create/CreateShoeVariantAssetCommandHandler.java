package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.create;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetPosition;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateShoeVariantAssetCommandHandler
        implements CommandHandler<CreateShoeVariantAssetCommand, Void> {

    private final ShoeVariantAssetCreator creator;

    public CreateShoeVariantAssetCommandHandler(ShoeVariantAssetCreator creator) {
        this.creator = creator;
    }

    @Override
    public Void handle(CreateShoeVariantAssetCommand command) {
        this.creator.create(
                new ShoeVariantAssetId(command.id()),
                new ShoeVariantAssetPosition(command.position()),
                new ShoeVariantId(command.shoeVariantId()),
                command.asset(),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
