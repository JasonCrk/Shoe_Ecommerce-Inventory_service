package com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;

import java.util.Optional;

public interface ShoeModelRepository {
    ShoeModel save(ShoeModel shoeModel);
    Optional<ShoeModel> findById(ShoeModelId id);
}
