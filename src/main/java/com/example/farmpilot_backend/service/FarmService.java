package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.FarmDto;
import com.example.farmpilot_backend.dto.FarmRequest;

import java.util.List;

public interface FarmService {
    FarmDto createFarm(FarmRequest request);
    FarmDto getFarmById(Long id);
    List<FarmDto> getAllFarms();
    FarmDto updateFarm(Long id, FarmRequest request);
    void deleteFarm(Long id);
}
