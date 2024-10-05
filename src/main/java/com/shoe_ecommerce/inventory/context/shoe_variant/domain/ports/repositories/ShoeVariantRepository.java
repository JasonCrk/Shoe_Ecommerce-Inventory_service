package com.shoe_ecommerce.inventory.context.shoe_variant.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;

import java.util.List;

public interface ShoeVariantRepository {
    ShoeVariant save(ShoeVariant variant);
    List<ShoeVariant> saveAll(List<ShoeVariant> variants);
}
