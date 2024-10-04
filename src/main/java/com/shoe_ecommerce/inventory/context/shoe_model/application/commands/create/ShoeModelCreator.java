package com.shoe_ecommerce.inventory.context.shoe_model.application.commands.create;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelDescription;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelName;
import com.shoe_ecommerce.inventory.context.category.domain.exceptions.CategoryNotExist;
import com.shoe_ecommerce.inventory.context.category.domain.ports.repositories.CategoryRepository;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

import com.shoe_ecommerce.inventory.context.shared.domain.exceptions.BrandNotExist;
import com.shoe_ecommerce.inventory.context.shared.domain.ports.services.BrandService;
import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeModelCreator {

    private final ShoeModelRepository shoeModelRepository;
    private final CategoryRepository categoryRepository;
    private final BrandService brandService;

    public ShoeModelCreator(
            ShoeModelRepository shoeModelRepository,
            CategoryRepository categoryRepository,
            BrandService brandService
    ) {
        this.shoeModelRepository = shoeModelRepository;
        this.categoryRepository = categoryRepository;
        this.brandService = brandService;
    }

    public void create(
            ShoeModelId id,
            ShoeModelName name,
            ShoeModelDescription description,
            CategoryId categoryId,
            BrandId brandId
    ) {
        boolean categoryNotExist = !categoryRepository.existsById(categoryId);
        if (categoryNotExist) throw new CategoryNotExist(categoryId);

        boolean brandNotExist = !brandService.existsById(brandId);
        if (brandNotExist) throw new BrandNotExist(brandId);

        ShoeModel shoeModel = ShoeModel.create(id, name, description, categoryId, brandId);
        shoeModelRepository.save(shoeModel);
    }
}
