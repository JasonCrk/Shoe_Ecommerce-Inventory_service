package com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.create.CreateShoeInventoryCommand;
import com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.requests.CreateShoeInventoryRequest;

import com.shoe_ecommerce.inventory.shared.domain.UuidGenerator;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Shoe inventory - POSTs")
@RestController
@RequestMapping("/api/v1/shoe-inventories")
public class ShoeInventoryPostController extends RestApiController {

    private final UuidGenerator uuidGenerator;

    public ShoeInventoryPostController(CommandBus commandBus, QueryBus queryBus, UuidGenerator uuidGenerator) {
        super(commandBus, queryBus);
        this.uuidGenerator = uuidGenerator;
    }

    @Operation(operationId = "Create a shoe inventory")
    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody CreateShoeInventoryRequest request,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
       this.dispatch(new CreateShoeInventoryCommand(
               associatedBrandId,
               uuidGenerator.generate(),
               request.shoeVariantId(),
               request.size(),
               request.stock()
       ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
