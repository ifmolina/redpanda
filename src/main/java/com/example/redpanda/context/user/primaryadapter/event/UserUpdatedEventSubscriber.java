package com.example.redpanda.context.user.primaryadapter.event;

import com.example.redpanda.context.user.domain.event.UserUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserUpdatedEventSubscriber {

    private final Logger logger = LoggerFactory.getLogger(UserUpdatedEventSubscriber.class);

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "users.user.updated",
            groupId = "redpanda-app"
    )
    void on(UserUpdatedEvent event) {
        logger.info("Message received: " + event.toString());
    }

}
