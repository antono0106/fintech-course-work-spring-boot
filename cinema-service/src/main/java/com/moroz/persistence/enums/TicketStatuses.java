package com.moroz.persistence.enums;

public enum TicketStatuses {
    NEW(1L, "NEW"),
    PROCESSING(2L, "PROCESSING"),
    DONE(3L, "DONE"),
    FAILED(4L, "FAILED");

    private Long id;
    private String name;

    TicketStatuses(Long id, String name) {
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
