package com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.delete.DeleteShoeInventoryCommand;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Shoe inventory - DELETEs")
@RestController
@RequestMapping("/api/v1/shoe-inventories")
public class ShoeInventoryDeleteController extends RestApiController {

    public ShoeInventoryDeleteController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") UUID shoeInventoryId,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new DeleteShoeInventoryCommand(shoeInventoryId.toString(), associatedBrandId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
