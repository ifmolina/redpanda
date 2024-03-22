package com.example.redpanda.context.user.domain.create;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.UserRepository;
import com.example.redpanda.library.domainevents.DomainEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserCreator {

    private final UserRepository repository;
    private final DomainEventPublisher domainEventPublisher;

    public UserCreator(
            UserRepository repository,
            DomainEventPublisher domainEventPublisher) {
        this.repository = repository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void create(
            User.Id id,
            User.Name name
    ) {

        final User user = User.create(id, name);
        repository.save(user);
        publishEvents(user);
    }

    private void publishEvents(User user) {
        user.pullEvents().forEach(domainEventPublisher::publish);
    }
}
