package com.maksimkaxxl.userregistrationeventdriven.messaging;

import com.maksimkaxxl.userregistrationeventdriven.config.RabbitConfig;
import com.maksimkaxxl.userregistrationeventdriven.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void send(UserCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                event
        );
    }
}
