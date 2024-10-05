package com.shoe_ecommerce.inventory.context.shoe_variant.domain;

import java.util.List;

public interface ShoeVariantRepository {
    ShoeVariant save(ShoeVariant variant);
    List<ShoeVariant> saveAll(List<ShoeVariant> variants);
}
