package com.example.farmpilot_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FarmOverviewDto {
    private long totalActivePigs;
    private long expectedBirthsThisMonth;
    private String topExpenseCategory;
}