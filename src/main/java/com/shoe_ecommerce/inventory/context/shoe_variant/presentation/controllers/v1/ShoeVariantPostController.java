package com.shoe_ecommerce.inventory.context.shoe_variant.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.create.CreateShoeVariantCommand;
import com.shoe_ecommerce.inventory.context.shoe_variant.presentation.requests.CreateShoeVariantRequest;

import com.shoe_ecommerce.inventory.shared.domain.UuidGenerator;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shoe variants - POSTs")
@RestController
@RequestMapping("/api/v1/shoe-variants")
public final class ShoeVariantPostController extends RestApiController {

    private final UuidGenerator uuidGenerator;

    public ShoeVariantPostController(CommandBus commandBus, QueryBus queryBus, UuidGenerator uuidGenerator) {
        super(commandBus, queryBus);
        this.uuidGenerator = uuidGenerator;
    }

    @Operation(operationId = "Create shoe variant")
    @PostMapping
    public ResponseEntity<String> create(
            @Valid CreateShoeVariantRequest request,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new CreateShoeVariantCommand(
                uuidGenerator.generate(),
                associatedBrandId,
                request.shoeModelId(),
                request.name(),
                request.price()
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
