package com.example.redpanda.context.user.secondaryadapter.database;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.UserRepository;
import com.example.redpanda.context.user.secondaryadapter.database.jpa.JpaUser;
import com.example.redpanda.context.user.secondaryadapter.database.jpa.JpaUserRepository;
import org.springframework.stereotype.Component;

@Component
public class PersistenceUserRepository implements UserRepository {
    private final JpaUserRepository jpaRepository;


    public PersistenceUserRepository(JpaUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(User user) {
        jpaRepository.save(JpaUser.toJpa(user));
    }

    @Override
    public boolean existsBy(User.Id id) {
        return jpaRepository.existsById(id.value());
    }

    @Override
    public User findBy(User.Id id) {
        return jpaRepository.findById(id.value()).get().toDomain();
    }

    @Override
    public void update(User user) {
        jpaRepository.save(JpaUser.toJpa(user));
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(JpaUser.toJpa(user));
    }
}
