package com.example.redpanda.library.domainevents;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.reflections.Reflections;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;
import java.util.Map;

public class KafkaEventDeserializer implements Deserializer<DomainEvent> {

    final Map<String, Class<? extends DomainEvent>> eventsMap = initEventsMap();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public DomainEvent deserialize(String topic, byte[] data) {
        throw new RuntimeException(new OperationNotSupportedException("Not implemented"));
    }

    @Override
    public DomainEvent deserialize(String topic, Headers headers, byte[] data) {
        try {
            final String type = new String(headers.lastHeader("type").value());
            return objectMapper
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .readValue(data, getDeserializationClass(type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Class<? extends DomainEvent> getDeserializationClass(String type) {
        return eventsMap.get(type);
    }

    private Map<String, Class<? extends DomainEvent>> initEventsMap() {
        final Map<String, Class<? extends DomainEvent>> eventsMapping = new HashMap<>();
        new Reflections("com.example.redpanda")
                .getSubTypesOf(DomainEvent.class).forEach(it -> {
                    try {
                        final String type = ((String)it.getDeclaredMethod("type").invoke(it.getDeclaredConstructor().newInstance()));
                        eventsMapping.put(type, it);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return eventsMapping;
    }
}
