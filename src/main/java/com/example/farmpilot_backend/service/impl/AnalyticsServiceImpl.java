package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.FinancialReportDto;
import com.example.farmpilot_backend.repository.ExpenseRepository;
import com.example.farmpilot_backend.repository.FeedConsumptionRepository;
import com.example.farmpilot_backend.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ExpenseRepository expenseRepository;
    private final FeedConsumptionRepository feedConsumptionRepository;

    @Override
    @Transactional(readOnly = true)
    public FinancialReportDto getMonthlyFinancialReport(int year, int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12.");
        }

        BigDecimal directExpenses = expenseRepository.sumExpensesByMonthAndYear(month, year);
        BigDecimal feedCost = feedConsumptionRepository.sumFeedCostByMonthAndYear(month, year);
        BigDecimal totalCost = directExpenses.add(feedCost);

        return FinancialReportDto.builder()
                .year(year)
                .month(month)
                .directExpenses(directExpenses)
                .feedCost(feedCost)
                .totalCost(totalCost)
                .build();
    }
}