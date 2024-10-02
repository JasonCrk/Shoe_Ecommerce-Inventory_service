package com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaCategory {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(200)", nullable = false, unique = true)
    private String name;
}
