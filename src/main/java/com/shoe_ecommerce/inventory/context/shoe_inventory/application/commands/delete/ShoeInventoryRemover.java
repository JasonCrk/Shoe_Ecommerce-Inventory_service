package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions.ShoeInventoryNotExist;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;
import com.shoe_ecommerce.inventory.shared.domain.domain_events.shoe_inventory.ShoeInventoryDeletedDomainEvent;

import java.util.Collections;

@Service
public final class ShoeInventoryRemover {

    private final ShoeInventoryRepository shoeInventoryRepository;
    private final EventBus eventBus;

    public ShoeInventoryRemover(ShoeInventoryRepository shoeInventoryRepository, EventBus eventBus) {
        this.shoeInventoryRepository = shoeInventoryRepository;
        this.eventBus = eventBus;
    }

    public void remove(ShoeInventoryId id, BrandId associatedBrandId) {
        BrandId brandId = shoeInventoryRepository.getBrandIdById(id)
                .orElseThrow(() -> new ShoeInventoryNotExist(id));

        if (!brandId.equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(associatedBrandId);

        shoeInventoryRepository.deleteById(id);

        this.eventBus.publish(Collections.singletonList(new ShoeInventoryDeletedDomainEvent(id.value())));
    }
}
