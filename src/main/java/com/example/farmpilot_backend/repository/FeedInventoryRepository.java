package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.FeedInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FeedInventoryRepository extends JpaRepository<FeedInventory, Long> {
    List<FeedInventory> findByNumberInKgLessThan(BigDecimal threshold);
    @Query("SELECT COALESCE(SUM(f.numberInKg), 0) FROM FeedInventory f WHERE f.farm.id = :farmId")
    Double sumTotalFeedInKgByFarmId(@Param("farmId") Long farmId);
    @Query("SELECT COALESCE(SUM(f.numberInKg * f.costPerKg), 0) FROM FeedInventory f WHERE f.farm.id = :farmId")
    Double sumTotalFeedCostByFarmId(@Param("farmId") Long farmId);
}