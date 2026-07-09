package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Pregnancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PregnancyRepository extends JpaRepository<Pregnancy, Long> {
    @Query("SELECT COUNT(p) FROM Pregnancy p WHERE EXTRACT(MONTH FROM p.expectedBirthDate) = :month AND " +
            "EXTRACT(YEAR FROM p.expectedBirthDate) = :year AND p.status = 'PLANNED'")
    long countPlannedBirthsByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT p FROM Pregnancy p WHERE p.status = 'PLANNED' AND p.expectedBirthDate = :targetDate")
    List<Pregnancy> findUpcomingBirths(@Param("targetDate") LocalDate targetDate);
}
