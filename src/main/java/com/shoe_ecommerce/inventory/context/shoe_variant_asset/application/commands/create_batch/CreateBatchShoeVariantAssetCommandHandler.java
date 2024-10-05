package com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.create_batch;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateBatchShoeVariantAssetCommandHandler
        implements CommandHandler<CreateBatchShoeVariantAssetCommand, Void> {

    private final ShoeVariantAssetBatchCreator creator;

    public CreateBatchShoeVariantAssetCommandHandler(ShoeVariantAssetBatchCreator creator) {
        this.creator = creator;
    }

    @Override
    public Void handle(CreateBatchShoeVariantAssetCommand command) {
        this.creator.createBatch(
                new BrandId(command.associatedBrandId()),
                new ShoeVariantId(command.shoeVariantId()),
                command.assets()
        );

        return null;
    }
}
