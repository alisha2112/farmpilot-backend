package com.example.farmpilot_backend.service;

public interface NotificationLogService {
    public void saveSystemAlert(String message);
    public void saveUserAction(String username, String actionMessage);
}
