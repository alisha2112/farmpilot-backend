package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.dto.InseminationRequest;
import com.example.farmpilot_backend.dto.PregnancyDto;
import com.example.farmpilot_backend.dto.PregnancyRequest;
import com.example.farmpilot_backend.service.PregnancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pregnancies")
@RequiredArgsConstructor
public class PregnancyController {
    private final PregnancyService pregnancyService;

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @PostMapping
    public ResponseEntity<PregnancyDto> createPregnancy(@RequestBody PregnancyRequest request) {
        PregnancyDto createdPregnancy = pregnancyService.createPregnancy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPregnancy);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping("/{id}")
    public ResponseEntity<PregnancyDto> getPregnancyById(@PathVariable Long id) {
        PregnancyDto pregnancy = pregnancyService.getPregnancyById(id);
        return ResponseEntity.ok(pregnancy);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping
    public ResponseEntity<List<PregnancyDto>> getAllPregnancies() {
        List<PregnancyDto> pregnancies = pregnancyService.getAllPregnancies();
        return ResponseEntity.ok(pregnancies);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<PregnancyDto> updatePregnancy(@PathVariable Long id, @RequestBody PregnancyRequest request) {
        PregnancyDto updatedPregnancy = pregnancyService.updatePregnancy(id, request);
        return ResponseEntity.ok(updatedPregnancy);
    }

    @PreAuthorize("hasRole('FARMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePregnancy(@PathVariable Long id) {
        pregnancyService.deletePregnancy(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('VETERINARIAN')")
    @PostMapping("/inseminate")
    public ResponseEntity<PregnancyDto> recordInsemination(@Valid @RequestBody InseminationRequest request) {
        PregnancyDto result = pregnancyService.recordInsemination(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}