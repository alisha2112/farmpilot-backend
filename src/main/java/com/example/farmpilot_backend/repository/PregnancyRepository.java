package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Pregnancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PregnancyRepository extends JpaRepository<Pregnancy, Long> {
    @Query("SELECT COUNT(p) FROM Pregnancy p WHERE EXTRACT(MONTH FROM p.expectedBirthDate) = :month AND " +
            "EXTRACT(YEAR FROM p.expectedBirthDate) = :year AND p.status = 'PLANNED'")
    long countPlannedBirthsByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
