package com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.category.domain.enums.Gender;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ShoeModel;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.ports.repositories.ShoeModelRepository;
import com.shoe_ecommerce.inventory.context.shoe_model.domain.value_objects.ShoeModelId;
import com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.mappers.ShoeModelMapper;
import com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.repositories.JpaShoeModelRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public final class ShoeModelRepositoryAdapter implements ShoeModelRepository {

    private final JpaShoeModelRepository repository;

    public ShoeModelRepositoryAdapter(JpaShoeModelRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ShoeModel save(ShoeModel shoeModel) {
        return ShoeModelMapper.toEntity(
                repository.save(ShoeModelMapper.toModel(shoeModel))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoeModel> findById(ShoeModelId id) {
        return repository.findById(id.uuid()).map(ShoeModelMapper::toEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Gender> getCategoryGenderById(ShoeModelId id) {
        return repository.getCategoryGenderById(id.uuid());
    }

    @Override
    @Transactional
    public void deleteById(ShoeModelId id) {
        repository.deleteById(id.uuid());
    }
}
