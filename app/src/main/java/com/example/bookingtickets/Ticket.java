package com.example.bookingtickets;

public class Ticket {
    private final Bus bus;
    private final int amount;
    private final String name;

    private Ticket(Bus bus, int amount, String name) {
        this.bus = bus;
        this.amount = amount;
        this.name = name;
    }

    public static Ticket of(Bus bus, int amount, String name) {
        return new Ticket(bus, amount, name);
    }
}
