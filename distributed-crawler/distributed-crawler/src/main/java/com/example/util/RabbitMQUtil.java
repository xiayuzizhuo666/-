package com.example.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQUtil {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String queueName, Object message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}