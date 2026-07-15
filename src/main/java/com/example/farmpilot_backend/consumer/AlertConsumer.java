package com.example.farmpilot_backend.consumer;

import com.example.farmpilot_backend.config.RabbitMQConfig;
import com.example.farmpilot_backend.service.NotificationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AlertConsumer {
    private final NotificationLogService notificationLogService;
    private final SimpMessagingTemplate messagingTemplate;
    @RabbitListener(queues = RabbitMQConfig.QUEUE_ALERTS)
    public void receiveAlert(String message) {
        log.info("[RABBITMQ LISTENER] Caught new alert: {}", message);
        notificationLogService.saveSystemAlert(message);
        messagingTemplate.convertAndSend("/topic/alerts", message);
        log.info("[WEBSOCKET] Сповіщення успішно трансльовано у канал /topic/alerts");
    }
}