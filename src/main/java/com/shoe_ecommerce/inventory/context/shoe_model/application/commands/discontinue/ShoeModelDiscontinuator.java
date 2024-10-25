package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.discontinue;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelAlreadyDiscontinued;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelIsNotPublished;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public final class ShoeModelDiscontinuator {

    private final ShoeModelRepository shoeModelRepository;

    private final EventBus eventBus;

    Logger logger = LoggerFactory.getLogger(ShoeModelDiscontinuator.class);

    public ShoeModelDiscontinuator(ShoeModelRepository shoeModelRepository, EventBus eventBus) {
        this.shoeModelRepository = shoeModelRepository;
        this.eventBus = eventBus;
    }

    public void discontinue(ShoeModelId shoeModelId, BrandId associatedBrandId) {
        ShoeModel shoeModel = shoeModelRepository.findById(shoeModelId)
                .orElseThrow(() -> new ShoeModelNotExist(shoeModelId));

        if (!shoeModel.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeModel.brandId());

        if (!shoeModel.isPublished().value())
            throw new ShoeModelIsNotPublished(shoeModelId);

        if (shoeModel.isDiscontinued().value())
            throw new ShoeModelAlreadyDiscontinued(shoeModelId);

        shoeModel.discontinue();

        shoeModelRepository.save(shoeModel);

        eventBus.publish(shoeModel.pullDomainEvents());

        logger.info(
                "The <{}> brand has discontinued the <{}> shoe model", associatedBrandId.value(), shoeModelId.value()
        );
    }
}
