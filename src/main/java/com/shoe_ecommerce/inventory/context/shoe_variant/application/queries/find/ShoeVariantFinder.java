package com.shoe_ecommerce.inventory.context.shoe_variant.application.queries.find;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelAlreadyDiscontinued;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantAlreadyDiscontinued;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeVariantFinder {

    private final ShoeVariantRepository shoeVariantRepository;
    private final ShoeModelRepository shoeModelRepository;

    public ShoeVariantFinder(ShoeVariantRepository shoeVariantRepository, ShoeModelRepository shoeModelRepository) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.shoeModelRepository = shoeModelRepository;
    }

    public ShoeVariantResponse find(ShoeVariantId id) {
        ShoeVariant shoeVariant = shoeVariantRepository.findById(id)
                .orElseThrow(() -> new ShoeVariantNotExist(id));

        if (shoeVariant.isDiscontinued().value()) throw new ShoeVariantAlreadyDiscontinued(id);

        ShoeModel shoeModel = shoeModelRepository.findById(shoeVariant.modelId())
                .orElseThrow(() -> new ShoeModelNotExist(shoeVariant.modelId()));

        if (shoeModel.isDiscontinued().value()) throw new ShoeModelAlreadyDiscontinued(shoeModel.id());

        return new ShoeVariantResponse(
                shoeVariant.id().value(),
                shoeVariant.brandId().value(),
                shoeVariant.modelId().value(),
                shoeVariant.name().value(),
                shoeVariant.price().value(),
                shoeVariant.isDiscontinued().value()
        );
    }
}
