package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.FeedConsumption;
import com.example.farmpilot_backend.entity.FeedInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface FeedConsumptionRepository extends JpaRepository<FeedConsumption, Long> {
    @Query("SELECT COALESCE(SUM(fc.amountKg * fc.feedInventory.costPerKg), 0) FROM FeedConsumption fc WHERE EXTRACT(MONTH FROM fc.date) = :month AND EXTRACT(YEAR FROM fc.date) = :year")
    BigDecimal sumFeedCostByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
