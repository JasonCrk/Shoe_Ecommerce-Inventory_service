package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.rabbitmq;

import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.EventBus;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
public final class RabbitMqEventBus implements EventBus {

    private final RabbitMqPublisher publisher;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    public void publish(DomainEvent domainEvent) {
        try {
            this.publisher.publish(domainEvent, exchangeName);
        } catch (AmqpException exception) {
            log.error(String.format("Erro when publishing the domain event <%s> <%s>", domainEvent.eventName(), domainEvent.eventId()));
        }
    }
}
