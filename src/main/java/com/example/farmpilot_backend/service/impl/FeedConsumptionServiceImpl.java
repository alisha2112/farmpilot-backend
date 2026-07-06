package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.FeedConsumptionDto;
import com.example.farmpilot_backend.dto.FeedConsumptionRequest;
import com.example.farmpilot_backend.entity.FeedConsumption;
import com.example.farmpilot_backend.entity.FeedInventory;
import com.example.farmpilot_backend.mapper.FeedConsumptionMapper;
import com.example.farmpilot_backend.repository.FeedConsumptionRepository;
import com.example.farmpilot_backend.repository.FeedInventoryRepository;
import com.example.farmpilot_backend.service.FeedConsumptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedConsumptionServiceImpl implements FeedConsumptionService {

    private final FeedConsumptionMapper feedConsumptionMapper;
    private final FeedConsumptionRepository feedConsumptionRepository;
    private final FeedInventoryRepository feedInventoryRepository;

    @Override
    @Transactional
    public FeedConsumptionDto createFeedConsumption(FeedConsumptionRequest request) {
        FeedInventory inventory = feedInventoryRepository.findById(request.getFeedInventoryId())
                .orElseThrow(() -> new EntityNotFoundException("Feed inventory with ID: " + request.getFeedInventoryId() + " not found."));

        BigDecimal currentStock = inventory.getNumberInKg();
        BigDecimal amountToConsume = request.getAmountKg();

        if (currentStock.compareTo(amountToConsume) < 0) {
            throw new IllegalArgumentException("Insufficient feed in stock! Remaining: " + currentStock + " kg");
        }

        inventory.setNumberInKg(currentStock.subtract(amountToConsume));
        feedInventoryRepository.save(inventory);

        FeedConsumption feedConsumption = feedConsumptionMapper.toEntity(request);
        FeedConsumption savedConsumption = feedConsumptionRepository.save(feedConsumption);

        return feedConsumptionMapper.toDto(savedConsumption);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedConsumptionDto getFeedConsumptionById(Long id) {
        FeedConsumption feedConsumption = feedConsumptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feed consumption with ID: " + id + " not found."));
        return feedConsumptionMapper.toDto(feedConsumption);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedConsumptionDto> getAllFeedConsumptions() {
        return feedConsumptionRepository.findAll().stream()
                .map(feedConsumptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FeedConsumptionDto updateFeedConsumption(Long id, FeedConsumptionRequest request) {
        FeedConsumption existingConsumption = feedConsumptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feed consumption with ID: " + id + " not found."));

        if (request.getAmountKg() != null) {
            existingConsumption.setAmountKg(request.getAmountKg());
        }

        FeedInventory newInventoryRef = new FeedInventory();
        newInventoryRef.setId(request.getFeedInventoryId());
        existingConsumption.setFeedInventory(newInventoryRef);

        FeedConsumption updatedConsumption = feedConsumptionRepository.save(existingConsumption);
        return feedConsumptionMapper.toDto(updatedConsumption);
    }

    @Override
    @Transactional
    public void deleteFeedConsumption(Long id) {
        if (!feedConsumptionRepository.existsById(id)) {
            throw new EntityNotFoundException("Feed consumption with ID: " + id + " not found.");
        }
        feedConsumptionRepository.deleteById(id);
    }
}