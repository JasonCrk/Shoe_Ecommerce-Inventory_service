package com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.models.JpaShoeInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaShoeInventoryRepository extends JpaRepository<JpaShoeInventory, UUID> {

    @Query(value = """
            SELECT sv.brandId FROM JpaShoeInventory si INNER JOIN JpaShoeVariant sv ON si.shoeVariantId = sv.id WHERE si.id = :id
            """)
    Optional<UUID> getBrandIdById(@Param("id") UUID id);
}
