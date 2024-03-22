package com.example.redpanda.context.user.domain.update;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.User.Id;
import com.example.redpanda.context.user.domain.UserRepository;
import com.example.redpanda.context.user.domain.error.UserNotFoundException;
import com.example.redpanda.library.domainevents.DomainEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserUpdater {

    private final UserRepository repository;
    private final DomainEventPublisher publisher;

    public UserUpdater(
            UserRepository repository, DomainEventPublisher publisher
    ) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void update(
            Id id,
            User.Name name
    ) {
        if (guardUserExists(id)) {
            final User user = repository.findBy(id);
            final User updatedUser = user.update(name);
            repository.update(updatedUser);
            publishEvents(updatedUser);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    private boolean guardUserExists(Id id) {
        return repository.existsBy(id);
    }

    private void publishEvents(User user) {
        user.pullEvents().forEach(publisher::publish);
    }
}
