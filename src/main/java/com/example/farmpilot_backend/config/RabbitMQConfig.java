package com.example.farmpilot_backend.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_ALERTS = "farm.alerts.queue";
    public static final String EXCHANGE_FARM = "farm.exchange";
    public static final String ROUTING_KEY_ALERTS = "farm.alerts.routing.key";

    @Bean
    public Queue alertsQueue() {
        return new Queue(QUEUE_ALERTS, true);
    }

    @Bean
    public DirectExchange farmExchange() {
        return new DirectExchange(EXCHANGE_FARM);
    }

    @Bean
    public Binding bindingAlerts(Queue alertsQueue, DirectExchange farmExchange) {
        return BindingBuilder.bind(alertsQueue).to(farmExchange).with(ROUTING_KEY_ALERTS);
    }
}