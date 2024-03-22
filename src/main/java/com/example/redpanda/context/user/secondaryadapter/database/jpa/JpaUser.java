package com.example.redpanda.context.user.secondaryadapter.database.jpa;

import com.example.redpanda.context.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(schema = "USERS", name = "USERS")
public class JpaUser {

    @Id
    private final UUID id;
    private final String name;


    protected JpaUser() {
        id = null;
        name = null;
    }

    public JpaUser(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User toDomain() {
        return new User(
                new User.Id(this.id),
                new User.Name(this.name)
        );
    }

    public static JpaUser toJpa(User user) {
        return new JpaUser(
                user.getId().value(),
                user.getName().value()
        );
    }
}
