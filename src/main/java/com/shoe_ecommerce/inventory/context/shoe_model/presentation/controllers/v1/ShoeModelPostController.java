package com.shoe_ecommerce.inventory.context.shoe_model.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_model.application.commands.create.CreateShoeModelCommand;
import com.shoe_ecommerce.inventory.context.shoe_model.presentation.requests.CreateShoeModelRequest;

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

@Tag(name = "POSTs")
@RestController
@RequestMapping("/api/v1/shoe-models")
public final class ShoeModelPostController extends RestApiController {

    private final UuidGenerator uuidGenerator;

    public ShoeModelPostController(CommandBus commandBus, QueryBus queryBus, UuidGenerator uuidGenerator) {
        super(commandBus, queryBus);
        this.uuidGenerator = uuidGenerator;
    }

    @Operation(operationId = "Create a shoe model and their variants")
    @PostMapping
    public ResponseEntity<String> create(
            @Valid CreateShoeModelRequest request,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new CreateShoeModelCommand(
                uuidGenerator.generate(),
                request.name(),
                request.description(),
                request.categoryId(),
                associatedBrandId,
                request.variants()
                        .stream()
                        .map(variant -> CreateShoeModelCommand.ShoeVariant.instance(
                                uuidGenerator.generate(),
                                variant.name(),
                                variant.price()
                        ))
                        .toList()
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
