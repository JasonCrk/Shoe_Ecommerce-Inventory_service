package com.shoe_ecommerce.inventory.context.shared.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "${microservices.brand-service}")
public interface BrandMicroserviceClient {

    @GetMapping(value = "/api/v1/brands/{id}/check")
    ResponseEntity<Void> existsById(@PathVariable("id") UUID id);
}
