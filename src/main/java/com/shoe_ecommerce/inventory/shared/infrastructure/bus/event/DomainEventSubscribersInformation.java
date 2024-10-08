package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event;

import com.shoe_ecommerce.inventory.shared.domain.Service;
import com.shoe_ecommerce.inventory.shared.domain.bus.event.DomainEventSubscriber;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

@Service
public final class DomainEventSubscribersInformation {
    HashMap<Class<?>, DomainEventSubscriberInformation> information;

    public DomainEventSubscribersInformation(HashMap<Class<?>, DomainEventSubscriberInformation> information) {
        this.information = information;
    }

    public DomainEventSubscribersInformation() {
        this(scanDomainEventSubscribers());
    }

    private static HashMap<Class<?>, DomainEventSubscriberInformation> scanDomainEventSubscribers() {
        Set<Class<?>> subscribers;

        try {
            subscribers = getTypesAnnotatedWith("com.shoe_ecommerce", DomainEventSubscriber.class);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        HashMap<Class<?>, DomainEventSubscriberInformation> subscribersInformation = new HashMap<>();

        for (Class<?> subscriberClass : subscribers) {
            DomainEventSubscriber annotation = subscriberClass.getAnnotation(DomainEventSubscriber.class);

            subscribersInformation.put(
                    subscriberClass,
                    new DomainEventSubscriberInformation(subscriberClass, Arrays.asList(annotation.value()))
            );
        }

        return subscribersInformation;
    }

    private static Set<Class<?>> getTypesAnnotatedWith(String packageName, Class<? extends Annotation> annotation)
            throws ClassNotFoundException, IOException {
        Set<Class<?>> annotatedClasses = new HashSet<>();

        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
        List<File> directories = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            directories.add(new File(resource.getFile()));
        }

        for (File directory : directories) {
            annotatedClasses.addAll(findClassesWithAnnotation(directory, packageName, annotation));
        }

        return annotatedClasses;
    }

    private static Set<Class<?>> findClassesWithAnnotation(
            File directory,
            String packageName,
            Class<? extends Annotation> annotation
    ) throws ClassNotFoundException {
        Set<Class<?>> annotatedClasses = new HashSet<>();

        if (!directory.exists()) return annotatedClasses;

        File[] files = directory.listFiles();
        if (files == null) return annotatedClasses;

        for (File file : files) {
            if (file.isDirectory()) {
                annotatedClasses.addAll(
                        findClassesWithAnnotation(file, packageName + "." + file.getName(), annotation)
                );
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(annotation) && !Modifier.isAbstract(clazz.getModifiers())) {
                    annotatedClasses.add(clazz);
                }
            }
        }

        return annotatedClasses;
    }

    public Collection<DomainEventSubscriberInformation> all() {
        return information.values();
    }

    public String[] rabbitMqFormattedNames() {
        return information.values()
                .stream()
                .map(DomainEventSubscriberInformation::formatRabbitMqQueueName)
                .distinct()
                .toArray(String[]::new);
    }
}
