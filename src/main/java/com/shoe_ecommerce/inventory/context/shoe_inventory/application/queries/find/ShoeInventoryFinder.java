package com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.find;

import com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.ShoeInventoryResponse;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions.ShoeInventoryNotExist;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeInventoryFinder {

    private final ShoeInventoryRepository shoeInventoryRepository;
    private final ShoeVariantRepository shoeVariantRepository;
    private final ShoeModelRepository shoeModelRepository;

    public ShoeInventoryFinder(
            ShoeInventoryRepository shoeInventoryRepository,
            ShoeVariantRepository shoeVariantRepository,
            ShoeModelRepository shoeModelRepository
    ) {
        this.shoeInventoryRepository = shoeInventoryRepository;
        this.shoeVariantRepository = shoeVariantRepository;
        this.shoeModelRepository = shoeModelRepository;
    }

    public ShoeInventoryResponse find(ShoeInventoryId id) {
        ShoeInventory shoeInventory = this.shoeInventoryRepository.findById(id)
                .orElseThrow(() -> new ShoeInventoryNotExist(id));

        ShoeVariant shoeVariant = this.shoeVariantRepository.findById(shoeInventory.variantId())
                .orElseThrow(() -> new ShoeVariantNotExist(shoeInventory.variantId()));

        if (shoeVariant.isDiscontinued().value()) throw new ShoeInventoryNotExist(id);

        ShoeModel shoeModel = this.shoeModelRepository.findById(shoeVariant.modelId())
                .orElseThrow(() -> new ShoeModelNotExist(shoeVariant.modelId()));

        if (shoeModel.isDiscontinued().value()) throw new ShoeModelNotExist(shoeModel.id());

        return new ShoeInventoryResponse(
                shoeInventory.id().value(),
                shoeInventory.stock().value(),
                new ShoeInventoryResponse.ShoeVariantResponse(
                        shoeVariant.id().value(),
                        shoeVariant.name().value(),
                        shoeVariant.price().value(),
                        shoeVariant.isDiscontinued().value(),
                        shoeModel.id().value()
                )
        );
    }
}
