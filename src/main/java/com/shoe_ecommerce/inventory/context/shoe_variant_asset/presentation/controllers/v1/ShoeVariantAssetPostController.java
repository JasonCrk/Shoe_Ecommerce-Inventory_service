package com.shoe_ecommerce.inventory.context.shoe_variant_asset.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_variant_asset.application.commands.create_batch.CreateBatchShoeVariantAssetCommand;
import com.shoe_ecommerce.inventory.context.shoe_variant_asset.presentation.requests.CreateBatchShoeVariantAssetRequest;

import com.shoe_ecommerce.inventory.shared.domain.MediaFile;
import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.MediaFileAdapter;
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

@Tag(name = "Shoe variant assets - POSTs")
@RestController
@RequestMapping("/api/v1/shoe-variant-assets")
public class ShoeVariantAssetPostController extends RestApiController {

    public ShoeVariantAssetPostController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Operation(operationId = "Create a batch of shoe variant assets")
    @PostMapping("/batch")
    public ResponseEntity<String> createBatch(
            @Valid CreateBatchShoeVariantAssetRequest request,
            @RequestHeader("X-User-Associated-Brand-Id") String associatedBrandId
    ) {
        this.dispatch(new CreateBatchShoeVariantAssetCommand(
                associatedBrandId,
                request.shoeVariantId(),
                request.assets().stream().<MediaFile>map(MediaFileAdapter::new).toList()
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
