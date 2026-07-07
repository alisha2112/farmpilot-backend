package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.PigDto;
import com.example.farmpilot_backend.dto.PigRequest;
import com.example.farmpilot_backend.entity.Farm;
import com.example.farmpilot_backend.entity.Pig;
import com.example.farmpilot_backend.mapper.PigMapper;
import com.example.farmpilot_backend.repository.PigRepository;
import com.example.farmpilot_backend.service.PigService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PigServiceImpl implements PigService {
    private static final int MIN_CASTRATION_AGE_DAYS = 14;
    private final PigMapper pigMapper;
    private final PigRepository pigRepository;

    @Override
    @Transactional
    public PigDto createPig(PigRequest request) {
        Pig pig = pigMapper.toEntity(request);
        Pig savedPig = pigRepository.save(pig);
        return pigMapper.toDto(savedPig);
    }

    @Override
    @Transactional(readOnly = true)
    public PigDto getPigById(Long id) {
        Pig pig = pigRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pig with ID: " + id + " not found."));
        return pigMapper.toDto(pig);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PigDto> getAllPigs() {
        return pigRepository.findAll().stream()
                .map(pigMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PigDto updatePig(Long id, PigRequest request) {
        Pig existingPig = pigRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pig with ID: " + id + " not found."));
        existingPig.setTagNumber(request.getTagNumber());
        if (request.getWeight() != null) {
            existingPig.setWeight(request.getWeight());
        }

        Farm newFarmRef = new Farm();
        newFarmRef.setId(request.getFarmId());
        existingPig.setFarm(newFarmRef);

        Pig updatedPig = pigRepository.save(existingPig);
        return pigMapper.toDto(updatedPig);
    }

    @Override
    @Transactional
    public void deletePig(Long id) {
        if (!pigRepository.existsById(id)) {
            throw new EntityNotFoundException("Pig with ID: " + id + " not found.");
        }
        pigRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PigDto> getCastrationCandidates() {
        LocalDate thresholdDate = LocalDate.now().minusDays(MIN_CASTRATION_AGE_DAYS);

        return pigRepository.findCastrationCandidates(thresholdDate).stream()
                .map(pigMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PigDto castratePig(Long id) {
        Pig pig = pigRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pig with ID " + id + " not found."));

        if (pig.getSex() == null || !pig.getSex().name().equals("MALE")) {
            throw new IllegalArgumentException("Only male pigs can be castrated.");
        }

        if (Boolean.TRUE.equals(pig.getCastration())) {
            throw new IllegalArgumentException("This pig is already castrated.");
        }

        long ageInDays = ChronoUnit.DAYS.between(pig.getDateOfBirth(), LocalDate.now());
        if (ageInDays < MIN_CASTRATION_AGE_DAYS) {
            throw new IllegalArgumentException("Pig is too young for castration. Minimum age is " +
                    MIN_CASTRATION_AGE_DAYS + " days. Current age: " + ageInDays + " days.");
        }

        pig.setCastration(true);
        Pig updatedPig = pigRepository.save(pig);

        return pigMapper.toDto(updatedPig);
    }
}
