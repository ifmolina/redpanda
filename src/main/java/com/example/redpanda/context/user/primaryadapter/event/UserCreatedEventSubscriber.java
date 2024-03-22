package com.example.redpanda.context.user.primaryadapter.event;

import com.example.redpanda.context.user.domain.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventSubscriber {

    private final Logger logger = LoggerFactory.getLogger(UserCreatedEventSubscriber.class);

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "users.user.created",
            groupId = "redpanda-app"
    )
    void on(UserCreatedEvent event) {
        logger.info("Message received: " + event.toString());
    }

}
