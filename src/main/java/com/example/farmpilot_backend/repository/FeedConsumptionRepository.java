package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.FeedInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedConsumptionRepository extends JpaRepository<FeedInventory, Long> {
}
