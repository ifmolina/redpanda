package com.example.redpanda.context.user.domain.event;

import com.example.redpanda.library.domainevents.DomainEvent;

import java.util.Objects;

public class UserUpdatedEvent extends DomainEvent {
    private final String id;
    private final String name;

    public UserUpdatedEvent(String id, String name) {
        super(id);
        this.id = id;
        this.name = name;
    }

    public UserUpdatedEvent() {
        super(null);
        this.id = null;
        this.name = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String type() {
        return "users.user.updated";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserUpdatedEvent that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserUpdatedEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
