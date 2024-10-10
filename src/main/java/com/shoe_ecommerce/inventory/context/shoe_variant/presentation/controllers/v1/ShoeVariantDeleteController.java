package com.shoe_ecommerce.inventory.context.shoe_variant.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.delete.DeleteShoeVariantCommand;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Shoe variant - DELETEs")
@RestController
@RequestMapping("/api/v1/shoe-variant")
public class ShoeVariantDeleteController extends RestApiController {

    public ShoeVariantDeleteController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Delete a shoe variant by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") UUID shoeVariantId,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new DeleteShoeVariantCommand(shoeVariantId.toString(), associatedBrandId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
