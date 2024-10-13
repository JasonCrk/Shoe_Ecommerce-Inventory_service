package com.shoe_ecommerce.inventory.context.shoe_variant.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_variant.application.commands.discontinue.DiscontinueShoeVariantCommand;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Shoe variant - PATCHs")
@RestController
@RequestMapping("/api/v1/shoe-variants")
public class ShoeVariantPatchController extends RestApiController {

    public ShoeVariantPatchController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Discontinue a shoe variant by ID")
    @PatchMapping("/{id}/discontinue")
    public ResponseEntity<String> discontinue(
            @PathVariable("id") UUID shoeVariantId,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new DiscontinueShoeVariantCommand(shoeVariantId.toString(), associatedBrandId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
