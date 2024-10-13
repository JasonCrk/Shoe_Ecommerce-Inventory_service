package com.shoe_ecommerce.inventory.shared.domain.domain_events.shoe_variant;

import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class ShoeVariantDiscontinuedDomainEvent extends DomainEvent {
    private final String id;

    public ShoeVariantDiscontinuedDomainEvent(String aggregateId, String eventId, String occurredOn, String id) {
        super(aggregateId, eventId, occurredOn);
        this.id = id;
    }

    public ShoeVariantDiscontinuedDomainEvent(String id) {
        super(id);
        this.id = id;
    }

    public ShoeVariantDiscontinuedDomainEvent() {
        super(null);
        this.id = null;
    }

    @Override
    public String eventName() {
        return "shoe_variant.discontinued";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("id", id);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new ShoeVariantDiscontinuedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("id")
        );
    }
}
