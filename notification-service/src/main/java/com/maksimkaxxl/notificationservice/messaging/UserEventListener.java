package com.maksimkaxxl.notificationservice.messaging;

import com.maksimkaxxl.notificationservice.config.RabbitConfig;
import com.maksimkaxxl.notificationservice.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventListener {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleUserCreated(UserCreatedEvent event) {

        log.info("Received user: {}", event);

        // Дописати email
    }
}