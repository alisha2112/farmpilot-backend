package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.service.AiConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiConsultationService aiConsultationService;

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping("/consult")
    public ResponseEntity<String> getConsultation(@RequestParam String question) {
        String answer = aiConsultationService.getConsultation(question);
        return ResponseEntity.ok(answer);
    }
}