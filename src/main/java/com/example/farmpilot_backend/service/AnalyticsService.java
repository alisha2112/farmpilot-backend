package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.FarmOverviewDto;
import com.example.farmpilot_backend.dto.FinancialReportDto;

public interface AnalyticsService {
    FinancialReportDto getMonthlyFinancialReport(int year, int month);
    FarmOverviewDto getFarmOverview();
}