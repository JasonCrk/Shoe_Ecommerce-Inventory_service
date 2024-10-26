package com.shoe_ecommerce.inventory.context.shared.infrastructure.adapters.services;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.ports.services.BrandService;
import com.shoe_ecommerce.inventory.context.shared.infrastructure.client.BrandMicroserviceClient;

import com.shoe_ecommerce.inventory.shared.domain.Service;

import org.springframework.http.ResponseEntity;

@Service
public class BrandServiceAdapter implements BrandService {

    private final BrandMicroserviceClient client;

    public BrandServiceAdapter(BrandMicroserviceClient client) {
        this.client = client;
    }

    @Override
    public boolean existsById(BrandId id) {
        ResponseEntity<Void> response = client.existsById(id.uuid());
        return response.getStatusCode().is2xxSuccessful();
    }
}
