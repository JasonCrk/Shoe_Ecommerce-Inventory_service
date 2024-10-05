package com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAsset;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.domain.ShoeVariantAssetRepository;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa.ShoeVariantAssetMapper;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa.JpaShoeVariantAssetRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.transaction.annotation.Transactional;

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
}