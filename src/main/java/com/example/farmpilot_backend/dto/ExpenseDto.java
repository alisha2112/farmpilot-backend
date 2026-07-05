package com.example.farmpilot_backend.dto;

import com.example.farmpilot_backend.entity.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseDto {
    private Long id;
    private String name;
    private BigDecimal expense;
    private LocalDate expenseDate;
    private ExpenseCategory category;
    private Long farmId;
}
