package com.maksimkaxxl.notificationservice.event;

import java.time.LocalDateTime;

public record UserCreatedEvent(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDateTime createdAt
) {}