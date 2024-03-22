package com.example.redpanda.context.user.domain;

import com.example.redpanda.context.user.domain.event.UserCreatedEvent;
import com.example.redpanda.context.user.domain.event.UserDeletedEvent;
import com.example.redpanda.context.user.domain.event.UserUpdatedEvent;
import com.example.redpanda.library.domainevents.Aggregate;

import java.util.Objects;
import java.util.UUID;

public class User extends Aggregate {

    private final Id id;
    private final Name name;

    public User(Id id, Name name) {
        this.id = id;
        this.name = name;
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public static User create(
            Id id,
            Name name
    ) {
        final User user = new User(id, name);
        user.pushEvent(new UserCreatedEvent(id.toString(), name.value));
        return user;
    }

    public User update(Name name) {
        final User updatedUser = new User(id, name);
        updatedUser.pushEvent(
                new UserUpdatedEvent(
                        updatedUser.getId().toString(), updatedUser.getName().toString()
                )
        );
        return updatedUser;
    }

    public User delete() {
        final User deletedUser = new User(id, name);
        deletedUser.pushEvent(
                new UserDeletedEvent(
                        deletedUser.getId().toString(), deletedUser.getName().toString()
                )
        );
        return deletedUser;
    }

    public record Id(UUID value) {

        @Override
        public String toString() {
            return value.toString();
        }

        public static Id from(String value) {
            return new Id(UUID.fromString(value));
        }
    }

    public record Name(String value) {
        @Override
        public String toString() {
            return value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
