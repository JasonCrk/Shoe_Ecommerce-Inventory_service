package com.shoe_ecommerce.inventory.context.shoe_model.domain;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.*;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

import java.util.Objects;

public final class ShoeModel {
    private final ShoeModelId id;
    private final ShoeModelName name;
    private final ShoeModelDescription description;
    private final ShoeModelIsPublished isPublished;
    private final ShoeModelDatePublished datePublished;
    private final CategoryId categoryId;
    private final BrandId brandId;

    public ShoeModel(
            ShoeModelId id,
            ShoeModelName name,
            ShoeModelDescription description,
            ShoeModelIsPublished isPublished,
            ShoeModelDatePublished datePublished,
            CategoryId categoryId,
            BrandId brandId
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublished = isPublished;
        this.datePublished = datePublished;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public static ShoeModel create(
            ShoeModelId id,
            ShoeModelName name,
            ShoeModelDescription description,
            CategoryId categoryId,
            BrandId brandId
    ) {
        ShoeModel shoeModel = new ShoeModel(
                id,
                name,
                description,
                new ShoeModelIsPublished(false),
                new ShoeModelDatePublished(),
                categoryId,
                brandId
        );
        return shoeModel;
    }

    public ShoeModelId id() {
        return id;
    }

    public ShoeModelName name() {
        return name;
    }

    public ShoeModelDescription description() {
        return description;
    }

    public ShoeModelIsPublished isPublished() {
        return isPublished;
    }

    public ShoeModelDatePublished datePublished() {
        return datePublished;
    }

    public CategoryId categoryId() {
        return categoryId;
    }

    public BrandId brandId() {
        return brandId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        ShoeModel shoeModel = (ShoeModel) object;
        return id.equals(shoeModel.id) &&
                name.equals(shoeModel.name) &&
                description.equals(shoeModel.description) &&
                isPublished.equals(shoeModel.isPublished) &&
                datePublished.equals(shoeModel.datePublished) &&
                categoryId.equals(shoeModel.categoryId) &&
                brandId.equals(shoeModel.brandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isPublished, datePublished, categoryId, brandId);
    }
}