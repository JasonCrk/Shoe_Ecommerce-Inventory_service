package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.delete;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.DeletePublishedShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public final class ShoeModelRemover {

    private final ShoeModelRepository shoeModelRepository;

    Logger logger = LoggerFactory.getLogger(ShoeModelRemover.class);

    public ShoeModelRemover(ShoeModelRepository shoeModelRepository) {
        this.shoeModelRepository = shoeModelRepository;
    }

    public void remove(ShoeModelId shoeModelId, BrandId associatedBrandId) {
        ShoeModel shoeModel = shoeModelRepository.findById(shoeModelId)
                .orElseThrow(() -> new ShoeModelNotExist(shoeModelId));

        if (!shoeModel.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeModel.brandId());

        if (shoeModel.isPublished().value())
            throw new DeletePublishedShoeModel(shoeModelId);

        shoeModelRepository.deleteById(shoeModelId);

        logger.info(
                "The <{}> brand has eliminated the <{}> shoe model", associatedBrandId.value(), shoeModelId.value()
        );
    }
}
