package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.FeedInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedInventoryRepository extends JpaRepository<FeedInventory, Long> {
}