package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.dto.HealthUpdateRequest;
import com.example.farmpilot_backend.dto.PigDto;
import com.example.farmpilot_backend.dto.PigRequest;
import com.example.farmpilot_backend.dto.SellPigRequest;
import com.example.farmpilot_backend.service.PigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pigs")
@RequiredArgsConstructor
public class PigController {
    private final PigService pigService;

    @PreAuthorize("hasRole('FARMER')")
    @PostMapping
    public ResponseEntity<PigDto> createPig(@RequestBody PigRequest request) {
        PigDto createdPig = pigService.createPig(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPig);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping("/{id}")
    public ResponseEntity<PigDto> getPigById(@PathVariable Long id) {
        PigDto pig = pigService.getPigById(id);
        return ResponseEntity.ok(pig);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping
    public ResponseEntity<List<PigDto>> getAllPigs() {
        List<PigDto> pigs = pigService.getAllPigs();
        return ResponseEntity.ok(pigs);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<PigDto> updatePig(@PathVariable Long id, @RequestBody PigRequest request) {
        PigDto updatedPig = pigService.updatePig(id, request);
        return ResponseEntity.ok(updatedPig);
    }

    @PreAuthorize("hasRole('FARMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePig(@PathVariable Long id) {
        pigService.deletePig(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping("/castration-candidates")
    public ResponseEntity<List<PigDto>> getCastrationCandidates() {
        List<PigDto> candidates = pigService.getCastrationCandidates();
        return ResponseEntity.ok(candidates);
    }

    @PreAuthorize("hasRole('VETERINARIAN')")
    @PatchMapping("/{id}/castrate")
    public ResponseEntity<PigDto> castratePig(@PathVariable Long id) {
        PigDto castratedPig = pigService.castratePig(id);
        return ResponseEntity.ok(castratedPig);
    }

    @PreAuthorize("hasRole('FARMER')")
    @PatchMapping("/{id}/sell")
    public ResponseEntity<PigDto> sellPig(
            @PathVariable Long id,
            @Valid @RequestBody SellPigRequest request) {
        PigDto soldPig = pigService.sellPig(id, request);
        return ResponseEntity.ok(soldPig);
    }

    @PreAuthorize("hasRole('VETERINARIAN')")
    @PatchMapping("/{id}/health")
    public ResponseEntity<PigDto> updateHealthRecord(
            @PathVariable Long id,
            @RequestBody HealthUpdateRequest request) {
        PigDto updatedPig = pigService.updateHealthRecord(id, request);
        return ResponseEntity.ok(updatedPig);
    }
}