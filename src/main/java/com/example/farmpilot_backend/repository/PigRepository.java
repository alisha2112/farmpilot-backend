package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Pig;
import com.example.farmpilot_backend.entity.enums.PigStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PigRepository extends JpaRepository<Pig, Long> {
    @Query("SELECT p FROM Pig p WHERE p.sex = 'MALE' AND p.castration = false AND p.dateOfBirth <= :thresholdDate")
    List<Pig> findCastrationCandidates(@Param("thresholdDate") LocalDate thresholdDate);

    long countByStatus(PigStatus status);
}
