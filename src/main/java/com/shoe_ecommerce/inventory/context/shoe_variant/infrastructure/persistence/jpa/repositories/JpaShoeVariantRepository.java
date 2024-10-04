package com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa.models.JpaShoeVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaShoeVariantRepository extends JpaRepository<JpaShoeVariant, UUID> {
}
