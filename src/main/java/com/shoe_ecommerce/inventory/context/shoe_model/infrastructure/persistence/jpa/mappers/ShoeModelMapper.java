package com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.mappers;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.*;
import com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.models.JpaShoeModel;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

public final class ShoeModelMapper {

    public static ShoeModel toEntity(JpaShoeModel model) {
        return new ShoeModel(
                new ShoeModelId(model.getId().toString()),
                new ShoeModelName(model.getName()),
                new ShoeModelDescription(model.getDescription()),
                new ShoeModelIsPublished(model.isPublished()),
                new ShoeModelDatePublished(model.getDatePublished()),
                new CategoryId(model.getCategoryId().toString()),
                new BrandId(model.getBrandId().toString())
        );
    }

    public static JpaShoeModel toModel(ShoeModel model) {
        return new JpaShoeModel(
                model.id().uuid(),
                model.name().value(),
                model.description().value(),
                model.isPublished().value(),
                model.categoryId().uuid(),
                model.brandId().uuid(),
                model.datePublished().value()
        );
    }
}
