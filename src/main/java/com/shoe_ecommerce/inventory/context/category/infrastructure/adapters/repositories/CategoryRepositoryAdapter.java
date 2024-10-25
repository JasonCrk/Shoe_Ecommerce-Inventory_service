package com.shoe_ecommerce.inventory.context.category.infrastructure.adapters.repositories;

import com.shoe_ecommerce.inventory.context.category.domain.Category;
import com.shoe_ecommerce.inventory.context.category.domain.ports.repositories.CategoryRepository;
import com.shoe_ecommerce.inventory.context.category.domain.value_objects.CategoryId;
import com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.mappers.CategoryMapper;
import com.shoe_ecommerce.inventory.context.category.infrastructure.persistence.jpa.repositories.JpaCategoryRepository;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final JpaCategoryRepository repository;

    public CategoryRepositoryAdapter(JpaCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(CategoryId id) {
        return repository.findById(id.uuid()).map(CategoryMapper::toEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(CategoryId id) {
        return repository.existsById(id.uuid());
    }
}
