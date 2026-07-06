package com.example.farmpilot_backend.controller;

import com.example.farmpilot_backend.dto.FeedInventoryDto;
import com.example.farmpilot_backend.dto.FeedInventoryRequest;
import com.example.farmpilot_backend.service.FeedInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feed-inventories")
@RequiredArgsConstructor
public class FeedInventoryController {
    private final FeedInventoryService feedInventoryService;

    @PostMapping
    public ResponseEntity<FeedInventoryDto> createFeedInventory(@RequestBody FeedInventoryRequest request) {
        FeedInventoryDto createdInventory = feedInventoryService.createFeedInventory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedInventoryDto> getFeedInventoryById(@PathVariable Long id) {
        FeedInventoryDto inventory = feedInventoryService.getFeedInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping
    public ResponseEntity<List<FeedInventoryDto>> getAllFeedInventories() {
        List<FeedInventoryDto> inventories = feedInventoryService.getAllFeedInventories();
        return ResponseEntity.ok(inventories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedInventoryDto> updateFeedInventory(@PathVariable Long id, @RequestBody FeedInventoryRequest request) {
        FeedInventoryDto updatedInventory = feedInventoryService.updateFeedInventory(id, request);
        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedInventory(@PathVariable Long id) {
        feedInventoryService.deleteFeedInventory(id);
        return ResponseEntity.noContent().build();
    }
}