package com.maksimkaxxl.notificationservice.service;

import com.maksimkaxxl.notificationservice.event.UserCreatedEvent;

public interface EmailService {

    void sendWelcomeEmail(UserCreatedEvent event);

}
