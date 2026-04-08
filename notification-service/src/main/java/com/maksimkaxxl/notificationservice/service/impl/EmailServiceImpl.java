package com.maksimkaxxl.notificationservice.service.impl;

import com.maksimkaxxl.notificationservice.event.UserCreatedEvent;
import com.maksimkaxxl.notificationservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendWelcomeEmail(UserCreatedEvent event) {

        log.info("Sending email to: {}", event.email());

        String message = """
                Hello %s %s,

                Your account has been successfully created.

                Regards,
                System
                """.formatted(event.firstName(), event.lastName());

        log.info("EMAIL CONTENT:\n{}", message);
    }
}