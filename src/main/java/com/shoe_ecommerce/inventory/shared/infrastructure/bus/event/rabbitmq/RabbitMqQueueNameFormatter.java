package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.rabbitmq;

import com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.DomainEventSubscriberInformation;

public final class RabbitMqQueueNameFormatter {

    public static String format(DomainEventSubscriberInformation information) {
        return information.formatRabbitMqQueueName();
    }
}
