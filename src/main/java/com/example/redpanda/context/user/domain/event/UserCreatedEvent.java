package com.example.redpanda.context.user.domain.event;

import com.example.redpanda.library.domainevents.DomainEvent;

import java.util.Objects;

public class UserCreatedEvent extends DomainEvent {
    private final String id;
    private final String name;

    public UserCreatedEvent(String id, String name) {
        super(id);
        this.id = id;
        this.name = name;
    }

    public UserCreatedEvent() {
        super("");
        id = null;
        name = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String type() {
        return "users.user.created";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCreatedEvent event)) return false;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
