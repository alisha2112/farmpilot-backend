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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pigs")
@RequiredArgsConstructor
public class PigController {
    private final PigService pigService;

    @PostMapping
    public ResponseEntity<PigDto> createPig(@RequestBody PigRequest request) {
        PigDto createdPig = pigService.createPig(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPig);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PigDto> getPigById(@PathVariable Long id) {
        PigDto pig = pigService.getPigById(id);
        return ResponseEntity.ok(pig);
    }

    @GetMapping
    public ResponseEntity<List<PigDto>> getAllPigs() {
        List<PigDto> pigs = pigService.getAllPigs();
        return ResponseEntity.ok(pigs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PigDto> updatePig(@PathVariable Long id, @RequestBody PigRequest request) {
        PigDto updatedPig = pigService.updatePig(id, request);
        return ResponseEntity.ok(updatedPig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePig(@PathVariable Long id) {
        pigService.deletePig(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/castration-candidates")
    public ResponseEntity<List<PigDto>> getCastrationCandidates() {
        List<PigDto> candidates = pigService.getCastrationCandidates();
        return ResponseEntity.ok(candidates);
    }

    @PatchMapping("/{id}/castrate")
    public ResponseEntity<PigDto> castratePig(@PathVariable Long id) {
        PigDto castratedPig = pigService.castratePig(id);
        return ResponseEntity.ok(castratedPig);
    }

    @PatchMapping("/{id}/sell")
    public ResponseEntity<PigDto> sellPig(
            @PathVariable Long id,
            @Valid @RequestBody SellPigRequest request) {
        PigDto soldPig = pigService.sellPig(id, request);
        return ResponseEntity.ok(soldPig);
    }

    @PatchMapping("/{id}/health")
    public ResponseEntity<PigDto> updateHealthRecord(
            @PathVariable Long id,
            @RequestBody HealthUpdateRequest request) {
        PigDto updatedPig = pigService.updateHealthRecord(id, request);
        return ResponseEntity.ok(updatedPig);
    }
}
