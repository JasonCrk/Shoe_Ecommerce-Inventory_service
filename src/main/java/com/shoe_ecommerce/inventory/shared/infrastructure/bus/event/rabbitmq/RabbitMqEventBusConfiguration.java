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
        String retryExchangeName = RabbitMqExchangeNameFormatter.retry(exchangeName);
        String deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(exchangeName);

        TopicExchange domainEventsExchange = new TopicExchange(exchangeName, true, false);
        TopicExchange retryDomainEventsExchange = new TopicExchange(retryExchangeName, true, false);
        TopicExchange deadLetterDomainEventsExchange = new TopicExchange(deadLetterExchangeName, true, false);

        List<Declarable> declarables = List.of(domainEventsExchange, retryDomainEventsExchange, deadLetterDomainEventsExchange);

        Collection<Declarable> queuesAndBindings = declareQueuesAndBindings(
                domainEventsExchange,
                retryDomainEventsExchange,
                deadLetterDomainEventsExchange
        ).stream().flatMap(Collection::stream).toList();

        declarables.addAll(queuesAndBindings);

        return new Declarables(declarables);
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindings(
            TopicExchange domainEventsExchange,
            TopicExchange retryDomainEventsExchange,
            TopicExchange deadLetterDomainEventsExchange
    ) {
        return domainEventSubscribersInformation.all().stream().map(information -> {
            String queueName           = RabbitMqQueueNameFormatter.format(information);

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
