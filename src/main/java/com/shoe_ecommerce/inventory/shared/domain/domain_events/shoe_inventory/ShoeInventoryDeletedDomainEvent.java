package com.shoe_ecommerce.inventory.shared.domain.domain_events.shoe_inventory;

import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class ShoeInventoryDeletedDomainEvent extends DomainEvent {

    private final String id;

    public ShoeInventoryDeletedDomainEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            String id
    ) {
        super(aggregateId, eventId, occurredOn);

        this.id = id;
    }

    public ShoeInventoryDeletedDomainEvent(String id) {
        super(id);
        this.id = id;
    }

    public ShoeInventoryDeletedDomainEvent() {
        super(null);
        this.id = null;
    }

    @Override
    public String eventName() {
        return "shoe_inventory.deleted";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("id", id);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            String occurredOn
    ) {
        return new ShoeInventoryDeletedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("id")
        );
    }

    public String id() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        ShoeInventoryDeletedDomainEvent that = (ShoeInventoryDeletedDomainEvent) object;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
