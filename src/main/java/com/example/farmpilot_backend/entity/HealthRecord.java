package com.example.farmpilot_backend.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HealthRecord implements Serializable {
    private List<String> vaccines = new ArrayList<>();
    private String lastCheckupDate;
    private String notes;
}
