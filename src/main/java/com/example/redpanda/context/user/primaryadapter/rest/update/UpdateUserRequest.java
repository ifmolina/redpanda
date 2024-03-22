package com.example.redpanda.context.user.primaryadapter.rest.update;

public record UpdateUserRequest(
        String id,
        String name
) { }
