package com.shoe_ecommerce.inventory.context.shared.domain.ports.services;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;

public interface BrandService {
    boolean existsById(BrandId id);
}
