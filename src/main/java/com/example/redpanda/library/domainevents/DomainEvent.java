package com.example.redpanda.library.domainevents;

public abstract class DomainEvent {

    protected DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.routingKey = aggregateId;
    }

    private final String aggregateId;

    private final String routingKey;

    public String getAggregateId() {
        return aggregateId;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public abstract String type();
}
