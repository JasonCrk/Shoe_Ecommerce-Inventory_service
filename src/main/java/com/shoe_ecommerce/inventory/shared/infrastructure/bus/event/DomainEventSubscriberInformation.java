package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;
import com.shoe_ecommerce.inventory.shared.domain.utils.StringFormatUtils;

import java.util.List;

@Service
public final class DomainEventSubscriberInformation {
    private final Class<?> subscriberClass;
    private final List<Class<? extends DomainEvent>> subscribedEvents;

    public DomainEventSubscriberInformation(
            Class<?> subscriberClass,
            List<Class<? extends DomainEvent>> subscribedEvents
    ) {
        this.subscriberClass = subscriberClass;
        this.subscribedEvents = subscribedEvents;
    }

    public Class<?> subscriberClass() {
        return subscriberClass;
    }

    public String contextName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[2];
    }

    public String moduleName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[3];
    }

    public String className() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    public List<Class<? extends DomainEvent>> subscribedEvents() {
        return subscribedEvents;
    }

    public String formatRabbitMqQueueName() {
        return String.format("shoe_ecommerce.%s.%s.%s", contextName(), moduleName(), StringFormatUtils.toSnake(className()));
    }
}
