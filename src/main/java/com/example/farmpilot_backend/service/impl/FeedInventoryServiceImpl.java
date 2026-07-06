package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.FeedInventoryDto;
import com.example.farmpilot_backend.dto.FeedInventoryRequest;
import com.example.farmpilot_backend.entity.Farm;
import com.example.farmpilot_backend.entity.FeedInventory;
import com.example.farmpilot_backend.mapper.FeedInventoryMapper;
import com.example.farmpilot_backend.repository.FeedInventoryRepository;
import com.example.farmpilot_backend.service.FeedInventoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedInventoryServiceImpl implements FeedInventoryService {

    private final FeedInventoryMapper feedInventoryMapper;
    private final FeedInventoryRepository feedInventoryRepository;

    @Override
    @Transactional
    public FeedInventoryDto createFeedInventory(FeedInventoryRequest request) {
        FeedInventory feedInventory = feedInventoryMapper.toEntity(request);
        FeedInventory savedInventory = feedInventoryRepository.save(feedInventory);
        return feedInventoryMapper.toDto(savedInventory);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedInventoryDto getFeedInventoryById(Long id) {
        FeedInventory feedInventory = feedInventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feed inventory with ID: " + id + " not found."));
        return feedInventoryMapper.toDto(feedInventory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedInventoryDto> getAllFeedInventories() {
        return feedInventoryRepository.findAll().stream()
                .map(feedInventoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FeedInventoryDto updateFeedInventory(Long id, FeedInventoryRequest request) {
        FeedInventory existingInventory = feedInventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feed inventory with ID: " + id + " not found."));

        existingInventory.setName(request.getName());

        Farm newFarmRef = new Farm();
        newFarmRef.setId(request.getFarmId());
        existingInventory.setFarm(newFarmRef);

        FeedInventory updatedInventory = feedInventoryRepository.save(existingInventory);
        return feedInventoryMapper.toDto(updatedInventory);
    }

    @Override
    @Transactional
    public void deleteFeedInventory(Long id) {
        if (!feedInventoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Feed inventory with ID: " + id + " not found.");
        }
        feedInventoryRepository.deleteById(id);
    }
}