package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.service.FarmStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final FarmStatisticsService farmStatisticsService;

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping("/monthly/{farmId}")
    public ResponseEntity<String> getMonthlyReport(@PathVariable Long farmId) {
        long startTime = System.currentTimeMillis();
        String report = farmStatisticsService.generateMonthlyReport(farmId);
        long endTime = System.currentTimeMillis();
        return ResponseEntity.ok(report + " (Час виконання: " + (endTime - startTime) + " мс)");
    }
}