package com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaShoeVariantAssetRepository extends JpaRepository<JpaShoeVariantAsset, UUID> {
}
