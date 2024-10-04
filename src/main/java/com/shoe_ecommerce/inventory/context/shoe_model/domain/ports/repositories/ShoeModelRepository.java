package com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;

public interface ShoeModelRepository {
    ShoeModel save(ShoeModel shoeModel);
}
