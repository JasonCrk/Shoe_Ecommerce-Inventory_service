package com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.rabbitmq;

import com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.DomainEventSubscribersInformation;
import com.shoe_ecommerce.inventory.shared.infrastructure.bus.event.DomainEventsInformation;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RabbitMqEventBusConfiguration {
    private final DomainEventSubscribersInformation domainEventSubscribersInformation;
    private final DomainEventsInformation domainEventsInformation;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    public RabbitMqEventBusConfiguration(
            DomainEventSubscribersInformation domainEventSubscribersInformation,
            DomainEventsInformation domainEventsInformation
    ) {
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
        this.domainEventsInformation = domainEventsInformation;
    }

    @Bean
    public Declarables declaration() {
        TopicExchange domainEventsExchange = new TopicExchange(exchangeName, true, false);

        List<Declarable> declarables = new ArrayList<>(List.of(domainEventsExchange));

        Collection<Declarable> queuesAndBindings = declareQueuesAndBindings(domainEventsExchange)
                .stream().flatMap(Collection::stream).toList();

        declarables.addAll(queuesAndBindings);

        return new Declarables(declarables);
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindings(TopicExchange domainEventsExchange) {
        return domainEventSubscribersInformation.all().stream().map(information -> {
            String queueName = RabbitMqQueueNameFormatter.format(information);

            Queue queue = QueueBuilder.durable(queueName).build();

            Binding fromExchangeSameQueueBinding = BindingBuilder
                    .bind(queue)
                    .to(domainEventsExchange)
                    .with(queueName);

            List<Binding> fromExchangeDomainEventsBindings = information.subscribedEvents().stream().map(
                    domainEventClass -> {
                        String eventName = domainEventsInformation.forClass(domainEventClass);
                        return BindingBuilder
                                .bind(queue)
                                .to(domainEventsExchange)
                                .with(eventName);
                    }).toList();

            List<Declarable> queuesAndBindings = new ArrayList<>();

            queuesAndBindings.add(queue);
            queuesAndBindings.add(fromExchangeSameQueueBinding);
            queuesAndBindings.addAll(fromExchangeDomainEventsBindings);

            return queuesAndBindings;
        }).collect(Collectors.toList());
    }
}
