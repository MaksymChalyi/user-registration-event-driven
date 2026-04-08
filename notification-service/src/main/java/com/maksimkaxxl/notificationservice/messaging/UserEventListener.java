package com.maksimkaxxl.notificationservice.messaging;

import com.maksimkaxxl.notificationservice.config.RabbitConfig;
import com.maksimkaxxl.notificationservice.event.UserCreatedEvent;
import com.maksimkaxxl.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserEventListener {

    private final EmailService emailService;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleUserCreated(UserCreatedEvent event) {

        log.info("Received user: {}", event);
        emailService.sendWelcomeEmail(event);
    }
}