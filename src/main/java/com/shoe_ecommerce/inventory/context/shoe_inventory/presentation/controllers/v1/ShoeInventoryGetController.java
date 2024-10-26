package com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.find.FindShoeInventoryQuery;
import com.shoe_ecommerce.inventory.context.shoe_inventory.application.queries.ShoeInventoryResponse;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Shoe inventory - GETs")
@RestController
@RequestMapping("/api/v1/shoe-inventories")
public class ShoeInventoryGetController extends RestApiController {

    public ShoeInventoryGetController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID shoeInventoryId) {
        ShoeInventoryResponse response = (ShoeInventoryResponse) this.ask(
                new FindShoeInventoryQuery(shoeInventoryId.toString())
        );

        return ResponseEntity.ok(response);
    }
}
