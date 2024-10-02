package com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.models.JpaShoeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaShoeModelRepository extends JpaRepository<JpaShoeModel, UUID> {
}
