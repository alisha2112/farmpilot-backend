package com.example.farmpilot_backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SellPigRequest {
    @NotNull(message = "Final weight is required for sale.")
    @DecimalMin(value = "0.1", message = "Final weight must be greater than 0.")
    private BigDecimal finalWeight;

    @NotNull(message = "Sale price is required.")
    @DecimalMin(value = "0.1", message = "Sale price must be greater than 0.")
    private BigDecimal salePrice;
}