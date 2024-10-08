package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;
import com.shoe_ecommerce.inventory.shared.domain.utils.JsonUtils;

import java.io.Serializable;
import java.util.HashMap;

@Service
public final class DomainEventJsonSerializer {

    public static String serialize(DomainEvent domainEvent) {
        HashMap<String, Serializable> attributes = domainEvent.toPrimitives();
        attributes.put("id", domainEvent.aggregateId());

        return JsonUtils.jsonEncode(new HashMap<String, Serializable>() {{
            put("data", new HashMap<String, Serializable>() {{
                put("id", domainEvent.eventId());
                put("type", domainEvent.eventName());
                put("occurred_on", domainEvent.occurredOn());
                put("attributes", attributes);
            }});

            put("meta", new HashMap<String, Serializable>());
        }});
    }
}
