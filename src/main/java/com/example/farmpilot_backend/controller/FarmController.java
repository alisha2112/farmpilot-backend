package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.dto.FarmDto;
import com.example.farmpilot_backend.dto.FarmRequest;
import com.example.farmpilot_backend.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<FarmDto> createFarm(@RequestBody FarmRequest request) {
        FarmDto createdFarm = farmService.createFarm(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
        FarmDto farm = farmService.getFarmById(id);
        return ResponseEntity.ok(farm);
    }

    @GetMapping
    public ResponseEntity<List<FarmDto>> getAllFarms() {
        List<FarmDto> farms = farmService.getAllFarms();
        return ResponseEntity.ok(farms);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDto> updateFarm(@PathVariable Long id, @RequestBody FarmRequest request) {
        FarmDto updatedFarm = farmService.updateFarm(id, request);
        return ResponseEntity.ok(updatedFarm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }
}
