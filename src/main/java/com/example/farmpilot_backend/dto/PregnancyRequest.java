package com.example.farmpilot_backend.dto;

import com.example.farmpilot_backend.entity.enums.PregnancyStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PregnancyRequest {
    private Long pigId;
    private LocalDate inseminationDate;
}
