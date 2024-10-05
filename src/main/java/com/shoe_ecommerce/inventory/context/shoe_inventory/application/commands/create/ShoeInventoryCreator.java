package com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.create;

import com.shoe_ecommerce.inventory.context.category.domain.enums.Gender;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.exceptions.SizeNotCorrespondingToGender;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventorySize;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryStock;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.exceptions.ShoeModelNotExist;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.exceptions.ShoeVariantNotExist;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.UnauthorizedAssociatedBrand;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeInventoryCreator {

    private final ShoeInventoryRepository shoeInventoryRepository;
    private final ShoeVariantRepository shoeVariantRepository;
    private final ShoeModelRepository shoeModelRepository;

    public ShoeInventoryCreator(
            ShoeInventoryRepository shoeInventoryRepository,
            ShoeVariantRepository shoeVariantRepository,
            ShoeModelRepository shoeModelRepository
    ) {
        this.shoeInventoryRepository = shoeInventoryRepository;
        this.shoeVariantRepository = shoeVariantRepository;
        this.shoeModelRepository = shoeModelRepository;
    }

    public void create(
            ShoeInventoryId id,
            ShoeVariantId shoeVariantId,
            ShoeInventorySize size,
            ShoeInventoryStock stock,
            BrandId associatedBrandId
    ) {
        ShoeVariant shoeVariant = shoeVariantRepository.findById(shoeVariantId)
                .orElseThrow(() -> new ShoeVariantNotExist(shoeVariantId));

        if (!shoeVariant.brandId().equals(associatedBrandId))
            throw new UnauthorizedAssociatedBrand(associatedBrandId);

        Gender gender = shoeModelRepository.getCategoryGenderById(shoeVariant.modelId())
                .orElseThrow(() -> new ShoeModelNotExist(shoeVariant.modelId()));

        if (gender.getMinSize() > size.value() || gender.getMaxSize() < size.value())
            throw new SizeNotCorrespondingToGender(size.value(), gender);

        this.shoeInventoryRepository.save(ShoeInventory.create(id, size, shoeVariantId, stock));
    }
}
