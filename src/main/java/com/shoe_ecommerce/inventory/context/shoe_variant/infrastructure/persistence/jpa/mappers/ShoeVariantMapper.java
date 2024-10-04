package com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.mappers;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantName;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantPrice;
import com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.models.JpaShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

public final class ShoeVariantMapper {

    public static ShoeVariant toEntity(JpaShoeVariant variant) {
        return new ShoeVariant(
                new ShoeVariantId(variant.getId().toString()),
                new ShoeModelId(variant.getShoeModelId().toString()),
                new ShoeVariantName(variant.getName()),
                new ShoeVariantPrice(variant.getPrice())
        );
    }

    public static JpaShoeVariant toModel(ShoeVariant variant) {
        return new JpaShoeVariant(
                variant.id().uuid(),
                variant.modelId().uuid(),
                variant.name().value(),
                variant.price().value()
        );
    }
}
