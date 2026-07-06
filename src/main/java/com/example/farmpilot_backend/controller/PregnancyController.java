package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.dto.PregnancyDto;
import com.example.farmpilot_backend.dto.PregnancyRequest;
import com.example.farmpilot_backend.service.PregnancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pregnancies")
@RequiredArgsConstructor
public class PregnancyController {
    private final PregnancyService pregnancyService;

    @PostMapping
    public ResponseEntity<PregnancyDto> createPregnancy(@RequestBody PregnancyRequest request) {
        PregnancyDto createdPregnancy = pregnancyService.createPregnancy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPregnancy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PregnancyDto> getPregnancyById(@PathVariable Long id) {
        PregnancyDto pregnancy = pregnancyService.getPregnancyById(id);
        return ResponseEntity.ok(pregnancy);
    }

    @GetMapping
    public ResponseEntity<List<PregnancyDto>> getAllPregnancies() {
        List<PregnancyDto> pregnancies = pregnancyService.getAllPregnancies();
        return ResponseEntity.ok(pregnancies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PregnancyDto> updatePregnancy(@PathVariable Long id, @RequestBody PregnancyRequest request) {
        PregnancyDto updatedPregnancy = pregnancyService.updatePregnancy(id, request);
        return ResponseEntity.ok(updatedPregnancy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        pregnancyService.deletePregnancy(id);
        return ResponseEntity.noContent().build();
    }
}
