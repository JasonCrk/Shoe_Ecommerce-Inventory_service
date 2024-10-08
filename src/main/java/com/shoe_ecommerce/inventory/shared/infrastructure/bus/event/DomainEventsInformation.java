package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEvent;
import com.shoe_ecommerce.inventory.shared.domain.bus.query.QueryHandler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public final class DomainEventsInformation {
    HashMap<String, Class<? extends DomainEvent>> indexedDomainEvents;

    public DomainEventsInformation() {
        try {
            Set<Class<? extends DomainEvent>> classes = findDomainEvents("com.shoe_ecommerce.inventory");
            indexedDomainEvents = formatEvents(classes);
        } catch (URISyntaxException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Class<? extends DomainEvent> forName(String name) {
        return indexedDomainEvents.get(name);
    }

    public String forClass(Class<? extends DomainEvent> domainEventClass) {
        return indexedDomainEvents.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), domainEventClass))
                .map(Map.Entry::getKey)
                .findFirst().orElse("");
    }

    private HashMap<String, Class<? extends DomainEvent>> formatEvents(Set<Class<? extends DomainEvent>> domainEvents)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HashMap<String, Class<? extends DomainEvent>> events = new HashMap<>();

        for (Class<? extends DomainEvent> domainEvent : domainEvents) {
            DomainEvent nullInstance = domainEvent.getConstructor().newInstance();

            events.put((String) domainEvent.getMethod("eventName").invoke(nullInstance), domainEvent);
        }

        return events;
    }

    private Set<Class<? extends DomainEvent>> findDomainEvents(String packageName) throws URISyntaxException, ClassNotFoundException {
        Set<Class<? extends DomainEvent>> handlers = new HashSet<>();

        String path = packageName.replace('.', '/');
        File directory = new File(getClass().getClassLoader().getResource(path).toURI());

        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                String className = file.getName();
                if (className.endsWith(".class")) {
                    String fullClassName = packageName + '.' + className.substring(0, className.length() - 6);
                    Class<?> clazz = Class.forName(fullClassName);
                    if (QueryHandler.class.isAssignableFrom(clazz)) {
                        handlers.add((Class<? extends DomainEvent>) clazz);
                    }
                }
            }
        }

        return handlers;
    }
}
