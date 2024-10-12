package com.shoe_ecommerce.inventory.context.shoe_variant.infrastructure.persistence.jpa;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "shoe_variants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaShoeVariant {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "brand_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID brandId;

    @Column(name = "shoe_model_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID shoeModelId;

    @Column(name = "name", columnDefinition = "VARCHAR(160)", nullable = false)
    private String name;

    @Column(name = "price", columnDefinition = "DECIMAL(8,2)", nullable = false)
    private BigDecimal price;

    @Column(name = "is_discontinued", columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private Boolean isDiscontinued;
}
