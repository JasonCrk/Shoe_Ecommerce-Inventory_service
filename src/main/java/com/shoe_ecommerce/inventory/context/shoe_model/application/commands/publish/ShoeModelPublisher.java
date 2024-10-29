package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.publish;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelIsAlreadyPublished;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotReadyForPublication;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;

import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;
import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.SomeShoeVariantsHaveNotAssetsAtAll;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public final class ShoeModelPublisher {

    private final ShoeModelRepository shoeModelRepository;
    private final ShoeInventoryRepository shoeInventoryRepository;
    private final ShoeVariantRepository shoeVariantRepository;

    private final EventBus eventBus;

    Logger logger = LoggerFactory.getLogger(ShoeModelPublisher.class);

    public ShoeModelPublisher(
            ShoeModelRepository shoeModelRepository,
            ShoeInventoryRepository shoeInventoryRepository,
            ShoeVariantRepository shoeVariantRepository,
            EventBus eventBus
    ) {
        this.shoeModelRepository = shoeModelRepository;
        this.shoeInventoryRepository = shoeInventoryRepository;
        this.shoeVariantRepository = shoeVariantRepository;
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

        Map<ShoeVariantId, Integer> shoeVariantsWithTotalAssets = shoeVariantRepository
                .findAllShoeVariantByShoeModelIdWithTotalAssets(shoeModelId);

        boolean someShoeVariantsHaveNoAssetsAtAll = shoeVariantsWithTotalAssets.values().stream()
                .anyMatch(totalAssets -> totalAssets == 0);

        if (someShoeVariantsHaveNoAssetsAtAll) throw new SomeShoeVariantsHaveNotAssetsAtAll(shoeModelId);

        shoeModel.publish();
        shoeModelRepository.save(shoeModel);

        eventBus.publish(shoeModel.pullDomainEvents());

        logger.info(
                "The <{}> brand has published the <{}> shoe model", associatedBrandId.value(), shoeModelId.value()
        );
    }
}
