package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.rabbitmq;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;
import com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.DomainEventJsonSerializer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public final class RabbitMqPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(DomainEvent domainEvent, String exchangeName) throws AmqpException {
        String serializeDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        Message message = new Message(
                serializeDomainEvent.getBytes(),
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .build()
        );

        rabbitTemplate.send(exchangeName, domainEvent.eventName(), message);
    }

    public void publish(Message message, String exchangeName, String routingKey) {
        rabbitTemplate.send(exchangeName, routingKey, message);
    }
}
