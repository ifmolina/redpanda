package com.example.redpanda.library.domainevents;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;


    public DomainEventPublisher(KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(DomainEvent domainEvent) {
        final String topic = domainEvent.type();
        final ProducerRecord<String, DomainEvent> producerRecord = new ProducerRecord<>(
                topic,
                null,
                domainEvent.getRoutingKey(),
                domainEvent,
                new RecordHeaders().add(new RecordHeader("type", domainEvent.type().getBytes()))
        );
        kafkaTemplate.send(producerRecord);
    }
}
