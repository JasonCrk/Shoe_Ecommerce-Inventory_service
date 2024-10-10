package com.shoe_ecommerce.inventory.context.shoe_model.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_model.application.commands.delete.DeleteShoeModelCommand;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Shoe model - DELETEs")
@RestController
@RequestMapping("/api/v1/shoe-models")
public class ShoeModelDeleteController extends RestApiController {

    public ShoeModelDeleteController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Delete a shoe model not published")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") UUID shoeModelId,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new DeleteShoeModelCommand(shoeModelId.toString(), associatedBrandId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
