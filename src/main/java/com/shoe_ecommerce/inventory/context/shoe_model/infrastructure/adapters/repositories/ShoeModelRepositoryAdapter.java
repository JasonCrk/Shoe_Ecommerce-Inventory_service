package com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.mappers.ShoeModelMapper;
import com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.repositories.JpaShoeModelRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class ShoeModelRepositoryAdapter implements ShoeModelRepository {

    private final JpaShoeModelRepository repository;

    public ShoeModelRepositoryAdapter(JpaShoeModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShoeModel save(ShoeModel shoeModel) {
        return ShoeModelMapper.toEntity(
                repository.save(ShoeModelMapper.toModel(shoeModel))
        );
    }
}
