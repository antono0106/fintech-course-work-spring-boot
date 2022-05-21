package com.moroz.persistence.enums;

public enum TicketStatus {
    NEW(1L, "NEW"),
    PROCESSING(2L, "PROCESSING"),
    DONE(3L, "DONE"),
    FAILED(4L, "FAILED");

    private final Long id;
    private final String name;

    TicketStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
