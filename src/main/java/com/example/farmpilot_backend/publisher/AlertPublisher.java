package com.example.farmpilot_backend.publisher;

import com.example.farmpilot_backend.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertPublisher {
    private final RabbitTemplate rabbitTemplate;
    public void publishAlert(String message) {
        log.info("Publishing message to RabbitMQ: {}", message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_FARM, RabbitMQConfig.ROUTING_KEY_ALERTS, message);
    }
}