package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.InseminationRequest;
import com.example.farmpilot_backend.dto.PregnancyDto;
import com.example.farmpilot_backend.dto.PregnancyRequest;

import java.util.List;

public interface PregnancyService {
    PregnancyDto createPregnancy(PregnancyRequest request);
    PregnancyDto getPregnancyById(Long id);
    List<PregnancyDto> getAllPregnancies();
    PregnancyDto updatePregnancy(Long id, PregnancyRequest request);
    void deletePregnancy(Long id);
    PregnancyDto recordInsemination(InseminationRequest request);
}
