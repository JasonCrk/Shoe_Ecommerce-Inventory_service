package com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_inventory.application.commands.update_stock.UpdateShoeInventoryStockCommand;
import com.shoe_ecommerce.inventory.context.shoe_inventory.presentation.requests.UpdateShoeInventoryStockRequest;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Shoe inventory - PATCHs")
@RestController
@RequestMapping("/api/v1/shoe-inventories")
public class ShoeInventoryPatchController extends RestApiController {

    public ShoeInventoryPatchController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Update shoe inventory stock by id")
    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(
            @PathVariable("id") UUID shoeInventoryId,
            @Valid @RequestBody UpdateShoeInventoryStockRequest request,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new UpdateShoeInventoryStockCommand(
                shoeInventoryId.toString(),
                request.newStock(),
                associatedBrandId
        ));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
