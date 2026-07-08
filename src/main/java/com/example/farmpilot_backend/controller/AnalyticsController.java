package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.dto.FarmOverviewDto;
import com.example.farmpilot_backend.dto.FinancialReportDto;
import com.example.farmpilot_backend.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/financial-report")
    public ResponseEntity<FinancialReportDto> getMonthlyReport(
            @RequestParam int year,
            @RequestParam int month) {
        FinancialReportDto report = analyticsService.getMonthlyFinancialReport(year, month);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/overview")
    public ResponseEntity<FarmOverviewDto> getFarmOverview() {
        FarmOverviewDto overview = analyticsService.getFarmOverview();
        return ResponseEntity.ok(overview);
    }
}