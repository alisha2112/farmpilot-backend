package com.example.farmpilot_backend.dto;

import com.example.farmpilot_backend.entity.enums.PregnancyStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PregnancyDto {
    private Long id;
    private LocalDate inseminationDate;
    private LocalDate expectedBirthDate;
    private LocalDate actualBirthDate;
    private PregnancyStatus status;
    private Long pigId;
}
