package com.example.redpanda.library.domainevents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aggregate {

    private List<DomainEvent> events = new ArrayList<>();

    public List<DomainEvent> pullEvents() {
        final List<DomainEvent> pulledEvents = events;
        events = Collections.EMPTY_LIST;
        return pulledEvents;
    }

    public void pushEvent(DomainEvent event) {
        this.events.add(event);
    }
}


