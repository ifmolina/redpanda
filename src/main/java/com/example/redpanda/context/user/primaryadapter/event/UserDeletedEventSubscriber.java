package com.example.redpanda.context.user.primaryadapter.event;

import com.example.redpanda.context.user.domain.event.UserDeletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserDeletedEventSubscriber {

    private final Logger logger = LoggerFactory.getLogger(UserDeletedEventSubscriber.class);

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "users.user.deleted",
            groupId = "redpanda-app"
    )
    void on(UserDeletedEvent event) {
        logger.info("Message received: " + event.toString());
    }

}
