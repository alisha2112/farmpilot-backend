package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.document.NotificationLog;
import com.example.farmpilot_backend.repository.NotificationLogRepository;
import com.example.farmpilot_backend.service.NotificationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationLogServiceImpl implements NotificationLogService {
    private final NotificationLogRepository notificationLogRepository;
    @Override
    public void saveSystemAlert(String message) {
        NotificationLog logEntry = NotificationLog.builder()
                .message(message)
                .type("SYSTEM_ALERT")
                .username("SYSTEM")
                .timestamp(LocalDateTime.now())
                .build();
        notificationLogRepository.save(logEntry);
        log.info("Saved system alert to MongoDB");
    }

    @Override
    public void saveUserAction(String username, String actionMessage) {
        NotificationLog logEntry = NotificationLog.builder()
                .message(actionMessage)
                .type("USER_ACTION")
                .username(username)
                .timestamp(LocalDateTime.now())
                .build();
        notificationLogRepository.save(logEntry);
        log.info("Saved user action to MongoDB for user: {}", username);
    }
}
