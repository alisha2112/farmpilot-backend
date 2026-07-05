package com.example.farmpilot_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FeedConsumptionRequest {
    private Long feedInventoryId;
    private BigDecimal amountKg;
}
