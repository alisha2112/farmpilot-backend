package com.example.farmpilot_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class FinancialReportDto {
    private int year;
    private int month;
    private BigDecimal directExpenses;
    private BigDecimal feedCost;
    private BigDecimal totalCost;
}