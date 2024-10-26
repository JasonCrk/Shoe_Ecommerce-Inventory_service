package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event;

import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;
import com.shoe_ecommerce.inventory.shared.domain.utils.StringFormatUtils;

import java.util.List;

public record DomainEventSubscriberInformation(
        Class<?> subscriberClass,
        List<Class<? extends DomainEvent>> subscribedEvents
) {
    public String moduleName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[4];
    }

    public String className() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    public String formatRabbitMqQueueName() {
        return String.format("shoe_ecommerce.%s.%s", moduleName(), StringFormatUtils.toSnake(className()));
    }
}
