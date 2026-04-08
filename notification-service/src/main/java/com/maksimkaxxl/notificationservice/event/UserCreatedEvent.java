package com.maksimkaxxl.notificationservice.event;

public record UserCreatedEvent(
        String firstName,
        String lastName,
        String email,
        String phone
) {}