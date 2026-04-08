package com.maksimkaxxl.userregistrationeventdriven.event;

public record UserCreatedEvent(
        String firstName,
        String lastName,
        String email,
        String phone
) {}