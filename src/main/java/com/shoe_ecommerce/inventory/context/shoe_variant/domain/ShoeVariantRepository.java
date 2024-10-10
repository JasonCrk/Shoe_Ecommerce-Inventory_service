package com.shoe_ecommerce.inventory.context.shoe_variant.domain;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;

import java.util.List;
import java.util.Optional;

public interface ShoeVariantRepository {
    Optional<ShoeVariant> findById(ShoeVariantId id);

    ShoeVariant save(ShoeVariant variant);
    List<ShoeVariant> saveAll(List<ShoeVariant> variants);

    void deleteById(ShoeVariantId id);
}
