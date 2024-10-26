package com.shoe_ecommerce.inventory.context.shoe_variant.presentation.controllers.v1;

import com.shoe_ecommerce.inventory.context.shoe_variant.application.queries.find.FindShoeVariantQuery;
import com.shoe_ecommerce.inventory.context.shoe_variant.application.queries.find.ShoeVariantResponse;

import com.shoe_ecommerce.inventory.shared.domain.bus.command.CommandBus;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryBus;
import com.shoe_ecommerce.inventory.shared.infrastructure.RestApiController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@Tag(name = "Shoe variant - GETs")
@RestController
@RequestMapping("/api/v1/shoe-variants")
public final class ShoeVariantGetController extends RestApiController {

    public ShoeVariantGetController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Serializable>> getById(
            @PathVariable("id") UUID shoeVariantId
    ) {
        ShoeVariantResponse response = (ShoeVariantResponse) this.ask(new FindShoeVariantQuery(shoeVariantId.toString()));

        return ResponseEntity.ok(new HashMap<>() {{
            put("id", response.id());
            put("name", response.name());
            put("brand_id", response.brandId());
            put("model_id", response.modelId());
            put("price", response.price());
            put("is_discontinued", response.isDiscontinued());
        }});
    }
}
