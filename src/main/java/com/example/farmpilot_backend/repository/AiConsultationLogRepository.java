package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.document.AiConsultationLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AiConsultationLogRepository extends MongoRepository<AiConsultationLog, String> {
    List<AiConsultationLog> findByUsernameOrderByTimestampDesc(String username);
}