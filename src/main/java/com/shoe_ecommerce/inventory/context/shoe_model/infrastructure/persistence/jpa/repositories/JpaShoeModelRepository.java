package com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.repositories;

import com.shoe_ecommerce.inventory.context.category.domain.enums.Gender;
import com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.models.JpaShoeModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaShoeModelRepository extends JpaRepository<JpaShoeModel, UUID> {

    @Query(value = """
            SELECT c.gender FROM JpaShoeModel s INNER JOIN Category c ON s.category_id = c.id WHERE s.id = :id
            """)
    Optional<Gender> getCategoryGenderById(@Param("id") UUID id);
}
