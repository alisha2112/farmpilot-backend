package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.document.NotificationLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationLogRepository extends MongoRepository<NotificationLog, String> {
    List<NotificationLog> findByUsernameOrderByTimestampDesc(String username);
}