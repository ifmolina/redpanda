package com.example.redpanda.context.user.domain.event;

import com.example.redpanda.library.domainevents.DomainEvent;

import java.util.Objects;

public class UserDeletedEvent extends DomainEvent {
    private final String id;
    private final String name;

    public UserDeletedEvent(String id, String name) {
        super(id);
        this.id = id;
        this.name = name;
    }

    public UserDeletedEvent() {
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
        return "users.user.deleted";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDeletedEvent that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserDeletedEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
