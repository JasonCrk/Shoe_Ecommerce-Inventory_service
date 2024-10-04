package com.shoe_ecommerce.inventory.context.category.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.category.domain.ports.repositories.CategoryRepository;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;
import com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.repositories.JpaCategoryRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public final class CategoryRepositoryAdapter implements CategoryRepository {

    private final JpaCategoryRepository repository;

    public CategoryRepositoryAdapter(JpaCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsById(CategoryId id) {
        return repository.existsById(id.uuid());
    }
}
