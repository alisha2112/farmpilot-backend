package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.FarmOverviewDto;
import com.example.farmpilot_backend.dto.FinancialReportDto;
import com.example.farmpilot_backend.entity.enums.PigStatus;
import com.example.farmpilot_backend.repository.ExpenseRepository;
import com.example.farmpilot_backend.repository.FeedConsumptionRepository;
import com.example.farmpilot_backend.repository.PigRepository;
import com.example.farmpilot_backend.repository.PregnancyRepository;
import com.example.farmpilot_backend.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ExpenseRepository expenseRepository;
    private final FeedConsumptionRepository feedConsumptionRepository;
    private final PigRepository pigRepository;
    private final PregnancyRepository pregnancyRepository;

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

    @Override
    @Transactional(readOnly = true)
    public FarmOverviewDto getFarmOverview() {
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();
        long activePigs = pigRepository.countByStatus(PigStatus.ACTIVE);
        long expectedBirths = pregnancyRepository.countPlannedBirthsByMonthAndYear(currentMonth, currentYear);
        String topCategory = expenseRepository.findTopCategoryByMonthAndYear(currentMonth, currentYear);

        if (topCategory == null) {
            topCategory = "NO_EXPENSES_YET";
        }

        return FarmOverviewDto.builder()
                .totalActivePigs(activePigs)
                .expectedBirthsThisMonth(expectedBirths)
                .topExpenseCategory(topCategory)
                .build();
    }
}