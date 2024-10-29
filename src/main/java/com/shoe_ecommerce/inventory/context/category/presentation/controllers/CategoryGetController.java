package com.shoe_ecommerce.inventory.context.category.presentation.controllers;

import com.shoe_ecommerce.inventory.context.category.application.queries.CategoryListResponse;
import com.shoe_ecommerce.inventory.context.category.application.queries.CategoryResponse;
import com.shoe_ecommerce.inventory.context.category.application.queries.search.SearchCategoriesQuery;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Categories - GETs")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryGetController extends RestApiController {

    public CategoryGetController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Get all categories", description = "Get all categories")
    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll() {
        return ResponseEntity.ok((CategoryListResponse) this.ask(new SearchCategoriesQuery()));
    }

    @Operation(operationId = "Get category by ID", description = "Get category by ID")
    @GetMapping()
    public ResponseEntity<CategoryResponse> find() {
        return ResponseEntity.ok((CategoryResponse) this.ask(new SearchCategoriesQuery()));
    }
}
