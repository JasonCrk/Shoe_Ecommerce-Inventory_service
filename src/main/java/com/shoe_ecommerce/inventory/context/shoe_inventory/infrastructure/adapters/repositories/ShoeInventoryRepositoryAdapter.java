package com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.mappers.ShoeInventoryMapper;
import com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.repositories.JpaShoeInventoryRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public final class ShoeInventoryRepositoryAdapter implements ShoeInventoryRepository {

    private final JpaShoeInventoryRepository repository;

    public ShoeInventoryRepositoryAdapter(JpaShoeInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ShoeInventory save(ShoeInventory shoeInventory) {
        return ShoeInventoryMapper.toEntity(
                repository.save(ShoeInventoryMapper.toModel(shoeInventory))
        );
    }
}
