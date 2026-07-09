package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
