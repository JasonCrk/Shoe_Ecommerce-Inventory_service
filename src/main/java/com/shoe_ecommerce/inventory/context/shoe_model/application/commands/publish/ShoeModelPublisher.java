package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.publish;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelIsAlreadyPublished;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotReadyForPublication;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;
import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;

@Service
public final class ShoeModelPublisher {

    private final ShoeModelRepository shoeModelRepository;
    private final ShoeInventoryRepository shoeInventoryRepository;

    private final EventBus eventBus;

    public ShoeModelPublisher(
            ShoeModelRepository shoeModelRepository,
            ShoeInventoryRepository shoeInventoryRepository,
            EventBus eventBus
    ) {
        this.shoeModelRepository = shoeModelRepository;
        this.shoeInventoryRepository = shoeInventoryRepository;
        this.eventBus = eventBus;
    }

    public void publish(ShoeModelId shoeModelId, BrandId associatedBrandId) {
        ShoeModel shoeModel = shoeModelRepository.findById(shoeModelId)
                .orElseThrow(() -> new ShoeModelNotExist(shoeModelId));

        if (shoeModel.isPublished().value())
            throw new ShoeModelIsAlreadyPublished(shoeModelId);

        if (!shoeModel.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(shoeModel.brandId());

        boolean shoeModelIsNotReadyForPublication = !shoeInventoryRepository.existsByShoeModelId(shoeModelId);

        if (shoeModelIsNotReadyForPublication) throw new ShoeModelNotReadyForPublication(shoeModelId);

        shoeModel.publish();
        shoeModelRepository.save(shoeModel);

        eventBus.publish(shoeModel.pullDomainEvents());
    }
}
