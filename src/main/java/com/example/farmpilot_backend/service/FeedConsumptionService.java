package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.FeedConsumptionDto;
import com.example.farmpilot_backend.dto.FeedConsumptionRequest;

import java.util.List;

public interface FeedConsumptionService {
    FeedConsumptionDto createFeedConsumption(FeedConsumptionRequest request);
    FeedConsumptionDto getFeedConsumptionById(Long id);
    List<FeedConsumptionDto> getAllFeedConsumptions();
    FeedConsumptionDto updateFeedConsumption(Long id, FeedConsumptionRequest request);
    void deleteFeedConsumption(Long id);
}