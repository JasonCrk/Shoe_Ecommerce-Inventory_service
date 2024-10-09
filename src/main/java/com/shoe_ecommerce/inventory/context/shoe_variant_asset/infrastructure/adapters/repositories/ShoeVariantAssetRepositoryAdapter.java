package com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.shoe_variant.domain.value_objects.ShoeVariantId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAsset;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAssetRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetId;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.value_objects.ShoeVariantAssetPosition;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa.ShoeVariantAssetMapper;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa.JpaShoeVariantAssetRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public final class ShoeVariantAssetRepositoryAdapter implements ShoeVariantAssetRepository {

    private final JpaShoeVariantAssetRepository repository;

    public ShoeVariantAssetRepositoryAdapter(JpaShoeVariantAssetRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ShoeVariantAsset save(ShoeVariantAsset shoeVariantAsset) {
        return ShoeVariantAssetMapper.toEntity(
                repository.save(ShoeVariantAssetMapper.toModel(shoeVariantAsset))
        );
    }

    @Override
    @Transactional
    public void deleteById(ShoeVariantAssetId id) {
        repository.deleteById(id.uuid());
    }

    @Override
    public void reduceByOneThePositionByShoeVariantAssetPosition(ShoeVariantId id, ShoeVariantAssetPosition position) {
        repository.reduceByOneThePositionByShoeVariantAssetPosition(id.uuid(), position.value());
    }

    @Override
    public void incrementByOneThePositionByShoeVariantIdAndGreaterThanOrEqualPosition(
            ShoeVariantId id,
            ShoeVariantAssetPosition position
    ) {
        repository.incrementByOneThePositionByShoeVariantIdAndGreaterThanOrEqualPosition(id.uuid(), position.value());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoeVariantAsset> findById(ShoeVariantAssetId id) {
        return repository.findById(id.uuid()).map(ShoeVariantAssetMapper::toEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByShoeVariantId(ShoeVariantId shoeVariantId) {
        return repository.countByShoeVariantId(shoeVariantId.uuid());
    }
}
