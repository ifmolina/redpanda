package com.example.redpanda.context.user.domain.find;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.User.Id;
import com.example.redpanda.context.user.domain.UserRepository;
import com.example.redpanda.context.user.domain.error.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserFinder {

    private final UserRepository repository;

    public UserFinder(UserRepository repository) {
        this.repository = repository;
    }

    public User find(
            Id id
    ) {
        if (!guardUserExists(id)) {
            throw new UserNotFoundException(id);
        }
        return repository.findBy(id);
    }

    private boolean guardUserExists(Id id) {
        return repository.existsBy(id);
    }



}