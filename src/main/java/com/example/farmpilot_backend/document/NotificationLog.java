package com.example.farmpilot_backend.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "notification_logs")
public class NotificationLog {
    @Id
    private String id;
    private String message;
    private String type;
    private String username;
    private LocalDateTime timestamp;
}