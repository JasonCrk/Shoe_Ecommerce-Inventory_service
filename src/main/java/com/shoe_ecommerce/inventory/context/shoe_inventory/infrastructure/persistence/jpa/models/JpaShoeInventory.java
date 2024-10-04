package com.shoe_ecommerce.inventory.context.shoe_inventory.infrastructure.persistence.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "shoe_inventories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaShoeInventory {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "size", columnDefinition = "DECIMAL(3,1)", nullable = false)
    private double size;

    @Column(name = "stock", columnDefinition = "MEDIUMINT UNSIGNED", nullable = false)
    private int stock;

    @Column(name = "shoe_variant_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID shoeVariantId;
}
