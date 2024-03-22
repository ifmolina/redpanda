package com.example.redpanda.context.user.domain.delete;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.User.Id;
import com.example.redpanda.context.user.domain.UserRepository;
import com.example.redpanda.context.user.domain.error.UserNotFoundException;
import com.example.redpanda.library.domainevents.DomainEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserDeleter {

    private final DomainEventPublisher domainEventPublisher;

    private final UserRepository repository;

    public UserDeleter(
            UserRepository repository,
            DomainEventPublisher domainEventPublisher
    ) {
        this.repository = repository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void delete(Id id) {
        if (!repository.existsBy(id)) {
            throw new UserNotFoundException(id);
        } else {
            final User user = repository.findBy(id).delete();
            repository.delete(user);
            publishEvents(user);
        }
    }

    private void publishEvents(User user) {
        user.pullEvents().forEach(domainEventPublisher::publish);
    }
}
