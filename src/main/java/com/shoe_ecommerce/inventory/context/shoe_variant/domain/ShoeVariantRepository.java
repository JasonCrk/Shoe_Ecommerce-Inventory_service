package com.shoe_ecommerce.inventory.context.shoe_variant.domain;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ShoeVariantRepository {
    Optional<ShoeVariant> findById(ShoeVariantId id);

    Map<ShoeVariantId, Integer> findAllShoeVariantByShoeModelIdWithTotalAssets(ShoeModelId shoeModelId);

    ShoeVariant save(ShoeVariant variant);
    List<ShoeVariant> saveAll(List<ShoeVariant> variants);

    void deleteById(ShoeVariantId id);
}
