package com.shoe_ecommerce.inventory.context.shoe_variant_asset.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "shoe_variant_assets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaShoeVariantAsset {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "shoe_variant_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID shoeVariantId;

    @Column(name = "url", columnDefinition = "TINYTEXT", nullable = false)
    private String url;

    @Column(name = "position", columnDefinition = "TINYINT", nullable = false)
    private int position;
}
