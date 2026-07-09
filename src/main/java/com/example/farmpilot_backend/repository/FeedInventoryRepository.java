package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.FeedInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FeedInventoryRepository extends JpaRepository<FeedInventory, Long> {
    List<FeedInventory> findByNumberInKgLessThan(BigDecimal threshold);
}