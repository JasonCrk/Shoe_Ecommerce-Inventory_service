package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;
import com.shoe_ecommerce.inventory.shared.domain.domain_events.shoe_variant.ShoeVariantDeletedDomainEvent;

import java.util.Collections;

@Service
public final class ShoeVariantRemover {

    private final ShoeVariantRepository shoeVariantRepository;

    private final EventBus eventBus;

    public ShoeVariantRemover(ShoeVariantRepository shoeVariantRepository, EventBus eventBus) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.eventBus = eventBus;
    }

    public void remove(ShoeVariantId id, BrandId associatedBrandId) {
        ShoeVariant shoeVariant = shoeVariantRepository.findById(id)
                .orElseThrow(() -> new ShoeVariantNotExist(id));

        if (!shoeVariant.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeVariant.brandId());

        shoeVariantRepository.deleteById(id);

        eventBus.publish(Collections.singletonList(new ShoeVariantDeletedDomainEvent(id.value())));
    }
}
