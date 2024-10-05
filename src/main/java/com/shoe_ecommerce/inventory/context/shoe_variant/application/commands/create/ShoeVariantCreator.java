package com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.create;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ports.repositories.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.*;

import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;
import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeVariantCreator {

    private final ShoeVariantRepository shoeVariantRepository;
    private final ShoeModelRepository shoeModelRepository;

    public ShoeVariantCreator(
            ShoeVariantRepository shoeVariantRepository,
            ShoeModelRepository shoeModelRepository
    ) {
        this.shoeVariantRepository = shoeVariantRepository;
        this.shoeModelRepository = shoeModelRepository;
    }

    public void create(
            ShoeVariantId id,
            ShoeModelId shoeModelId,
            ShoeVariantName name,
            ShoeVariantPrice price,
            BrandId associatedBrandId
    ) {
        ShoeModel shoeModel = this.shoeModelRepository.findById(shoeModelId)
                .orElseThrow(() -> new ShoeModelNotExist(shoeModelId));

        if (!shoeModel.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeModel.brandId());

        this.shoeVariantRepository.save(
                ShoeVariant.create(id, shoeModelId, name, price)
        );
    }
}
