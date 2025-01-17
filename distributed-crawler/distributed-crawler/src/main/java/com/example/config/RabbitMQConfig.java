package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME_0 = "crawl_tasks_0";
    public static final String QUEUE_NAME_1 = "crawl_tasks_1";
    public static final String QUEUE_NAME_2 = "crawl_tasks_2";

    @Bean
    public Queue queue0() {
        return new Queue(QUEUE_NAME_0, false);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME_1, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME_2, false);
    }
}