package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.spring;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Service
public class SpringBootEventBus implements EventBus {

    private final ApplicationEventPublisher publisher;

    public SpringBootEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    public void publish(DomainEvent domainEvent) {
        this.publisher.publishEvent(domainEvent);
    }
}
