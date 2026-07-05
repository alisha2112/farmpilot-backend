package com.example.farmpilot_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PigRequest {
    private String tagNumber;
    private Long farmId;
    private BigDecimal weight;
}
