package com.example.farmpilot_backend.dto;

import com.example.farmpilot_backend.entity.enums.PigStatus;
import com.example.farmpilot_backend.entity.enums.Sex;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PigDto {
    private Long id;
    private String tagNumber;
    private Sex sex;
    private BigDecimal weight;
    private LocalDate dateOfBirth;
    private Boolean castration;
    private PigStatus status;
    private Long farmId;
}