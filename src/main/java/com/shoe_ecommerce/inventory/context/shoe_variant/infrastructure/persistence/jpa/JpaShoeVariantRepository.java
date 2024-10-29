package com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaShoeVariantRepository extends JpaRepository<JpaShoeVariant, UUID> {

    @Query(value = """
            SELECT sv.id, COUNT(sva.id) FROM JpaShoeVariant\s
            LEFT JOIN JpaShoeVariantAsset sva ON sv.id = sva.shoeVariantId\s
            WHERE sv.shoeModelId = :shoeModelId\s
            GROUP BY sv.name\s
            """)
    List<Object[]> findAllShoeVariantsByShoeModelIdWithTotalAssets(@Param("shoeModelId") UUID shoeModelId);
}
