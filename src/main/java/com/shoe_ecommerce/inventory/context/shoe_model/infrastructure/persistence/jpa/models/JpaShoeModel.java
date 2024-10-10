package com.shoe_ecommerce.inventory.context.shoe_model.infrastructure.persistence.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "shoe_models")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaShoeModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(200)", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "is_published", columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private boolean isPublished;

    @Column(name = "is_discontinued", columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private boolean isDiscontinued;

    @Column(name = "category_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID categoryId;

    @Column(name = "brand_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID brandId;

    @Column(name = "date_published", columnDefinition = "DATE")
    private LocalDate datePublished;
}
