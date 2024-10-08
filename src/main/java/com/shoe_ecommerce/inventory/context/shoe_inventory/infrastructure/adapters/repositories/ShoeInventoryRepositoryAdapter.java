package com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ShoeInventory;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.ports.repositories.ShoeInventoryRepository;
import com.shoe_ecommerce.inventory.context.shoe_inventory.domain.value_objects.ShoeInventoryId;
import com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.mappers.ShoeInventoryMapper;
import com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.repositories.JpaShoeInventoryRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public final class ShoeInventoryRepositoryAdapter implements ShoeInventoryRepository {

    private final JpaShoeInventoryRepository repository;

    public ShoeInventoryRepositoryAdapter(JpaShoeInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoeInventory> findById(ShoeInventoryId id) {
        return repository.findById(id.uuid()).map(ShoeInventoryMapper::toEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BrandId> getBrandIdById(ShoeInventoryId id) {
        return repository.getBrandIdById(id.uuid()).map(val -> new BrandId(val.toString()));
    }

    @Override
    @Transactional
    public ShoeInventory save(ShoeInventory shoeInventory) {
        return ShoeInventoryMapper.toEntity(
                repository.save(ShoeInventoryMapper.toModel(shoeInventory))
        );
    }

    @Override
    @Transactional
    public void deleteById(ShoeInventoryId id) {
        repository.deleteById(id.uuid());
    }
}
