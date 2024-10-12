package com.shoe_ecommerce.inventory.context.shoe_model.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_model.application.commands.discontinue.DiscontinueShoeModelCommand;
import com.shoe_ecommerce.inventory.context.shoe_model.application.commands.publish.PublishShoeModelCommand;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Shoe model - PATCHs")
@RestController
@RequestMapping("/api/v1/shoe-models")
public class ShoeModelPatchController extends RestApiController {

    public ShoeModelPatchController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Discontinue a shoe model by id")
    @PatchMapping("/{id}/discontinue")
    public ResponseEntity<String> discontinue(
            @PathVariable("id") UUID shoeModelId,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new DiscontinueShoeModelCommand(shoeModelId.toString(), associatedBrandId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(operationId = "Publish a shoe model by id")
    @PatchMapping("/{id}/publish")
    public ResponseEntity<String> publish(
            @PathVariable("id") UUID shoeModelId,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new PublishShoeModelCommand(shoeModelId.toString(), associatedBrandId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
