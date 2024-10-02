package com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.models.JpaCategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCategoryRepository extends JpaRepository<JpaCategory, UUID> {
}
