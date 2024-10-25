package com.shoe_ecommerce.inventory.context.shared.infrastructure.adapters.services;

import com.shoe_ecommerce.inventory.context.shared.domain.BrandId;
import com.shoe_ecommerce.inventory.context.shared.domain.ports.services.BrandService;
import com.shoe_ecommerce.inventory.context.shared.infrastructure.client.BrandMicroserviceClient;

import com.shoe_ecommerce.inventory.shared.domain.Service;

@Service
public class BrandServiceAdapter implements BrandService {

    private final BrandMicroserviceClient client;

    public BrandServiceAdapter(BrandMicroserviceClient client) {
        this.client = client;
    }

    @Override
    public boolean existsById(BrandId id) {
        return client.existsById(id.uuid());
    }
}
