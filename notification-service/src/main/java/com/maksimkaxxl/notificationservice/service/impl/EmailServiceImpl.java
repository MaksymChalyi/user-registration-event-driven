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
        log.info("""
                        === EMAIL ===
                        To: {}
                        Subject: Welcome
                        Body: Hello {} {}
                        """,
                event.email(),
                event.firstName(),
                event.lastName()
        );
    }
}