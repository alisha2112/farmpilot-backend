package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.FeedInventoryDto;
import com.example.farmpilot_backend.dto.FeedInventoryRequest;

import java.util.List;

public interface FeedInventoryService {
    FeedInventoryDto createFeedInventory(FeedInventoryRequest request);
    FeedInventoryDto getFeedInventoryById(Long id);
    List<FeedInventoryDto> getAllFeedInventories();
    FeedInventoryDto updateFeedInventory(Long id, FeedInventoryRequest request);
    void deleteFeedInventory(Long id);
}