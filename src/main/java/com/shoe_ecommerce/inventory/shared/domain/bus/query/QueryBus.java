package com.shoe_ecommerce.inventory.shared.domain.bus.query;

public interface QueryBus {
    Response ask(Query query);
}
