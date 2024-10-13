package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.DeletePublishedShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;

@Service
public final class ShoeVariantRemover {

    private final ShoeVariantRepository shoeVariantRepository;
    private final ShoeModelRepository shoeModelRepository;

    private final EventBus eventBus;

    public ShoeVariantRemover(
            ShoeVariantRepository shoeVariantRepository,
            ShoeModelRepository shoeModelRepository,
            EventBus eventBus
    ) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.shoeModelRepository = shoeModelRepository;
        this.eventBus = eventBus;
    }

    public void remove(ShoeVariantId id, BrandId associatedBrandId) {
        ShoeVariant shoeVariant = shoeVariantRepository.findById(id)
                .orElseThrow(() -> new ShoeVariantNotExist(id));

        if (!shoeVariant.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeVariant.brandId());

        ShoeModel shoeModel = shoeModelRepository.findById(shoeVariant.modelId())
                .orElseThrow(() -> new ShoeModelNotExist(shoeVariant.modelId()));

        boolean shoeVariantIsPublished = shoeModel.isPublished().value() && !shoeVariant.isDiscontinued().value();

        if (shoeVariantIsPublished)
            throw new DeletePublishedShoeVariant(shoeVariant.id());

        shoeVariantRepository.deleteById(shoeVariant.id());
    }
}
