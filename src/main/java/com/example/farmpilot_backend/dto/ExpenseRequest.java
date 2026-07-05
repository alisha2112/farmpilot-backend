package com.example.farmpilot_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExpenseRequest {
    private String name;
    private Long farmId;
    private BigDecimal expense;
}
