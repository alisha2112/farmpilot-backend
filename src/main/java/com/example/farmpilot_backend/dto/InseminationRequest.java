package com.example.farmpilot_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class InseminationRequest {
    @NotNull(message = "The sow ID is mandatory for registering insemination.")
    private Long pigId;
    private LocalDate inseminationDate;
}