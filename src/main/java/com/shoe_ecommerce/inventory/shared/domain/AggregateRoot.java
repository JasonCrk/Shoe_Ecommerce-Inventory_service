package com.shoe_ecommerce.inventory.shared.domain;

import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private List<DomainEvent> domainEvents = new ArrayList<>();

    final public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = domainEvents;
        domainEvents = Collections.emptyList();
        return events;
    }

    final protected void record(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }
}
