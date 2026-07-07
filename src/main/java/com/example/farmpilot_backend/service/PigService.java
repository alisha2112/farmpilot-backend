package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.PigDto;
import com.example.farmpilot_backend.dto.PigRequest;

import java.util.List;

public interface PigService {
    PigDto createPig(PigRequest request);
    PigDto getPigById(Long id);
    List<PigDto> getAllPigs();
    PigDto updatePig(Long id, PigRequest request);
    void deletePig(Long id);
    List<PigDto> getCastrationCandidates();
    PigDto castratePig(Long id);
}
