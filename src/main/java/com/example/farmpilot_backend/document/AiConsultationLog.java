package com.example.farmpilot_backend.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "ai_consultation_logs")
public class AiConsultationLog {
    @Id
    private String id;
    private String username;
    private String question;
    private String answer;
    private LocalDateTime timestamp;
}