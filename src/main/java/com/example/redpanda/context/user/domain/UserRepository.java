package com.example.redpanda.context.user.domain;

import com.example.redpanda.context.user.domain.User.Id;

public interface UserRepository {

    void save(User user);
    boolean existsBy(Id id);
    User findBy(Id id);
    void update(User user);
    void delete(User user);
}
