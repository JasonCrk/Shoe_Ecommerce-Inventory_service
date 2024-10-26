package com.shoe_ecommerce.inventory.shared.domain.domain_events.shoe_model;

import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class ShoeModelDiscontinuedDomainEvent extends DomainEvent {
    private final String id;

    public ShoeModelDiscontinuedDomainEvent(String aggregateId, String eventId, String occurredOn, String id) {
        super(aggregateId, eventId, occurredOn);
        this.id = id;
    }

    public ShoeModelDiscontinuedDomainEvent(String id) {
        super(id);
        this.id = id;
    }

    public ShoeModelDiscontinuedDomainEvent() {
        super(null);
        this.id = null;
    }

    @Override
    public String eventName() {
        return "shoe_model.discontinued";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("id", id);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new ShoeModelDiscontinuedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("id")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ShoeModelDiscontinuedDomainEvent that = (ShoeModelDiscontinuedDomainEvent) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
