package com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.models.JpaShoeInventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaShoeInventoryRepository extends JpaRepository<JpaShoeInventory, UUID> {
}
