package com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JpaShoeVariantAssetRepository extends JpaRepository<JpaShoeVariantAsset, UUID> {

    @Modifying
    @Query(value = """
            UPDATE JpaShoeVariantAsset SET position = position - 1 WHERE shoeVariantId = :shoeVariantId AND position > :position
            """)
    void reduceByOneThePositionByShoeVariantAssetPosition(
            @Param("shoeVariantId") UUID shoeVariantId,
            @Param("position") int position
    );

    long countByShoeVariantId(UUID shoeVariantId);
}
