package com.example.redpanda.context.user.secondaryadapter.database.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends CrudRepository<JpaUser, UUID> {
}
