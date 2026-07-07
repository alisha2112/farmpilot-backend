package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.InseminationRequest;
import com.example.farmpilot_backend.dto.PregnancyDto;
import com.example.farmpilot_backend.dto.PregnancyRequest;
import com.example.farmpilot_backend.entity.Pig;
import com.example.farmpilot_backend.entity.Pregnancy;
import com.example.farmpilot_backend.entity.enums.PregnancyStatus;
import com.example.farmpilot_backend.mapper.PregnancyMapper;
import com.example.farmpilot_backend.repository.PigRepository;
import com.example.farmpilot_backend.repository.PregnancyRepository;
import com.example.farmpilot_backend.service.PregnancyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PregnancyServiceImpl implements PregnancyService {
    private final PregnancyMapper pregnancyMapper;
    private final PregnancyRepository pregnancyRepository;
    private final PigRepository pigRepository;

    @Override
    @Transactional
    public PregnancyDto createPregnancy(PregnancyRequest request) {
        Pregnancy pregnancy = pregnancyMapper.toEntity(request);
        Pregnancy savedPregnancy = pregnancyRepository.save(pregnancy);
        return pregnancyMapper.toDto(savedPregnancy);
    }

    @Override
    @Transactional(readOnly = true)
    public PregnancyDto getPregnancyById(Long id) {
        Pregnancy pregnancy = pregnancyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pregnancy with ID: " + id + " not found."));
        return pregnancyMapper.toDto(pregnancy);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PregnancyDto> getAllPregnancies() {
        return pregnancyRepository.findAll().stream()
                .map(pregnancyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PregnancyDto updatePregnancy(Long id, PregnancyRequest request) {
        Pregnancy existingPregnancy = pregnancyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pregnancy with ID: " + id + " not found."));
        if (request.getInseminationDate() != null) {
            existingPregnancy.setInseminationDate(request.getInseminationDate());
            existingPregnancy.setExpectedBirthDate(request.getInseminationDate().plusDays(114));
        }
        Pig newPigRef = new Pig();
        newPigRef.setId(request.getPigId());
        existingPregnancy.setPig(newPigRef);
        Pregnancy updatedPregnancy = pregnancyRepository.save(existingPregnancy);
        return pregnancyMapper.toDto(updatedPregnancy);
    }

    @Override
    @Transactional
    public void deletePregnancy(Long id) {
        if (!pregnancyRepository.existsById(id)) {
            throw new EntityNotFoundException("Pregnancy with ID: " + id + " not found.");
        }
        pregnancyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PregnancyDto recordInsemination(InseminationRequest request) {
        Pig pig = pigRepository.findById(request.getPigId())
                .orElseThrow(() -> new EntityNotFoundException("Pig with ID " + request.getPigId() + " not found"));

        if (pig.getSex() != null && pig.getSex().name().equals("MALE")) {
            throw new IllegalArgumentException("Cannot register pregnancy: the selected animal is male!");
        }

        LocalDate insemDate = request.getInseminationDate() != null ? request.getInseminationDate() : LocalDate.now();
        LocalDate expectedBirth = insemDate.plusDays(114);

        Pregnancy pregnancy = new Pregnancy();
        pregnancy.setPig(pig);
        pregnancy.setInseminationDate(insemDate);
        pregnancy.setExpectedBirthDate(expectedBirth);
        pregnancy.setStatus(PregnancyStatus.PLANNED);

        Pregnancy savedPregnancy = pregnancyRepository.save(pregnancy);
        return pregnancyMapper.toDto(savedPregnancy);
    }
}
