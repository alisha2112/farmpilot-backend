package com.example.farmpilot_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FeedConsumptionDto {
    private Long id;
    private BigDecimal amountKg;
    private LocalDate date;
    private Long feedInventoryId;
}
