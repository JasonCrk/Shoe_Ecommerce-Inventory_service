package com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.models;

import com.shoe_ecommerce.inventory.context.category.domain.enums.Gender;

import jakarta.persistence.*;

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

    @Column(name = "name", columnDefinition = "VARCHAR(200)", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "VARCHAR(30)", nullable = false)
    private Gender gender;
}
