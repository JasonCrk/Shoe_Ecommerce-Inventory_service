package com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ShoeVariant;
import com.shoe_ecommerce.inventory.context.shoe_variant.domain.ports.repositories.ShoeVariantRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.mappers.ShoeVariantMapper;
import com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.repositories.JpaShoeVariantRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public final class ShoeVariantRepositoryAdapter implements ShoeVariantRepository {

    private final JpaShoeVariantRepository repository;

    public ShoeVariantRepositoryAdapter(JpaShoeVariantRepository repository) {
        this.repository = repository;
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
}
