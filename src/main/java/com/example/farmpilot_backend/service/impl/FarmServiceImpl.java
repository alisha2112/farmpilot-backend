package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.FarmDto;
import com.example.farmpilot_backend.dto.FarmRequest;
import com.example.farmpilot_backend.entity.Farm;
import com.example.farmpilot_backend.mapper.FarmMapper;
import com.example.farmpilot_backend.repository.FarmRepository;
import com.example.farmpilot_backend.service.FarmService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    public FarmDto createFarm(FarmRequest request) {
        Farm farm = farmMapper.toEntity(request);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toDto(savedFarm);
    }

    @Override
    public FarmDto getFarmById(Long id) {
        Farm farm = farmRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Farm with ID: " + id + " not found."));
        return farmMapper.toDto(farm);
    }

    @Override
    public List<FarmDto> getAllFarms() {
        return farmRepository.findAll().stream()
                .map(farmMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FarmDto updateFarm(Long id, FarmRequest request) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm with ID: " + id + " not found."));
        existingFarm.setName(request.getName());
        Farm updatedFarm = farmRepository.save(existingFarm);
        return farmMapper.toDto(updatedFarm);
    }

    @Override
    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new EntityNotFoundException("Farm with ID: " + id + " not found.");
        }
        farmRepository.deleteById(id);
    }
}
