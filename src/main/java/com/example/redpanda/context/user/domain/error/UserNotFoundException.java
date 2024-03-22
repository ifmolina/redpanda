package com.example.redpanda.context.user.domain.error;

import com.example.redpanda.context.user.domain.User.Id;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Id id) {
        super("User " + id + " not found");
    }
}
