package com.example.redpanda.library.idgenerator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdGenerator {
    public UUID generate() {
        return UUID.randomUUID();
    }
}
