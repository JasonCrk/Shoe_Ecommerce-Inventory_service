package com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.ShoeVariantMapper;
import com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.JpaShoeVariantRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public final class ShoeVariantRepositoryAdapter implements ShoeVariantRepository {

    private final JpaShoeVariantRepository repository;

    public ShoeVariantRepositoryAdapter(JpaShoeVariantRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoeVariant> findById(ShoeVariantId id) {
        return repository.findById(id.uuid()).map(ShoeVariantMapper::toEntity);
    }

    @Override
    @Transactional
    public ShoeVariant save(ShoeVariant variant) {
        return ShoeVariantMapper.toEntity(
                repository.save(ShoeVariantMapper.toModel(variant))
        );
    }

    @Override
    @Transactional
    public List<ShoeVariant> saveAll(List<ShoeVariant> variants) {
        return repository.saveAll(variants.stream().map(ShoeVariantMapper::toModel).toList())
                .stream()
                .map(ShoeVariantMapper::toEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteById(ShoeVariantId id) {
        repository.deleteById(id.uuid());
    }
}
