package com.shoe_ecommerce.inventory.context.shoe_variant_asset.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.delete.DeleteShoeVariantAssetCommand;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Shoe variant assets - DELETEs")
@RestController
@RequestMapping("/api/v1/shoe-variant-assets")
public class ShoeVariantAssetDeleteController extends RestApiController {

    public ShoeVariantAssetDeleteController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") UUID id,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new DeleteShoeVariantAssetCommand(id.toString(), associatedBrandId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
