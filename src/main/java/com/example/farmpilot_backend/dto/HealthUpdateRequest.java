package com.example.farmpilot_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthUpdateRequest {
    private String newVaccine;
    private String checkupDate;
    private String newNote;
}