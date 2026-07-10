package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.dto.FeedConsumptionDto;
import com.example.farmpilot_backend.dto.FeedConsumptionRequest;
import com.example.farmpilot_backend.service.FeedConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feed-consumptions")
@RequiredArgsConstructor
public class FeedConsumptionController {
    private final FeedConsumptionService feedConsumptionService;

    @PreAuthorize("hasRole('FARMER')")
    @PostMapping
    public ResponseEntity<FeedConsumptionDto> createFeedConsumption(@RequestBody FeedConsumptionRequest request) {
        FeedConsumptionDto createdConsumption = feedConsumptionService.createFeedConsumption(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsumption);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping("/{id}")
    public ResponseEntity<FeedConsumptionDto> getFeedConsumptionById(@PathVariable Long id) {
        FeedConsumptionDto consumption = feedConsumptionService.getFeedConsumptionById(id);
        return ResponseEntity.ok(consumption);
    }

    @PreAuthorize("hasAnyRole('FARMER', 'VETERINARIAN')")
    @GetMapping
    public ResponseEntity<List<FeedConsumptionDto>> getAllFeedConsumptions() {
        List<FeedConsumptionDto> consumptions = feedConsumptionService.getAllFeedConsumptions();
        return ResponseEntity.ok(consumptions);
    }

    @PreAuthorize("hasRole('FARMER')")
    @PutMapping("/{id}")
    public ResponseEntity<FeedConsumptionDto> updateFeedConsumption(@PathVariable Long id, @RequestBody FeedConsumptionRequest request) {
        FeedConsumptionDto updatedConsumption = feedConsumptionService.updateFeedConsumption(id, request);
        return ResponseEntity.ok(updatedConsumption);
    }

    @PreAuthorize("hasRole('FARMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedConsumption(@PathVariable Long id) {
        feedConsumptionService.deleteFeedConsumption(id);
        return ResponseEntity.noContent().build();
    }
}