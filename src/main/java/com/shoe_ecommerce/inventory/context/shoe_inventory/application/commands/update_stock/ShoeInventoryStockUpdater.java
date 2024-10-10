package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.update_stock;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions.ShoeInventoryNotExist;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryStock;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeInventoryStockUpdater {

    private final ShoeInventoryRepository shoeInventoryRepository;
    private final ShoeVariantRepository shoeVariantRepository;

    public ShoeInventoryStockUpdater(
            ShoeInventoryRepository shoeInventoryRepository,
            ShoeVariantRepository shoeVariantRepository
    ) {
        this.shoeInventoryRepository = shoeInventoryRepository;
        this.shoeVariantRepository = shoeVariantRepository;
    }

    public void update(ShoeInventoryId shoeInventoryId, ShoeInventoryStock newStock, BrandId associatedBrandId) {
        ShoeInventory shoeInventory = shoeInventoryRepository.findById(shoeInventoryId)
                .orElseThrow(() -> new ShoeInventoryNotExist(shoeInventoryId));

        ShoeVariant shoeVariant = shoeVariantRepository.findById(shoeInventory.variantId())
                .orElseThrow(() -> new ShoeVariantNotExist(shoeInventory.variantId()));

        if (!shoeVariant.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeVariant.brandId());

        shoeInventory.updateStock(newStock);

        shoeInventoryRepository.save(shoeInventory);
    }
}
