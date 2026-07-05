package com.example.farmpilot_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FeedInventoryDto {
    private Long id;
    private String name;
    private BigDecimal costPerKg;
    private BigDecimal numberInKg;
    private Long farmId;
}
