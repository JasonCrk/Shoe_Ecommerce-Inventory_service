package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.update_stock;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryStock;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandHandler;

@Service
public final class UpdateShoeInventoryStockCommandHandler implements CommandHandler<UpdateShoeInventoryStockCommand, Void> {

    private final ShoeInventoryStockUpdater updater;

    public UpdateShoeInventoryStockCommandHandler(ShoeInventoryStockUpdater updater) {
        this.updater = updater;
    }

    @Override
    public Void handle(UpdateShoeInventoryStockCommand command) {
        this.updater.update(
                new ShoeInventoryId(command.shoeInventoryId()),
                new ShoeInventoryStock(command.newStock()),
                new BrandId(command.associatedBrandId())
        );

        return null;
    }
}
