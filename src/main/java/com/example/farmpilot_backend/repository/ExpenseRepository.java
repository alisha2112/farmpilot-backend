package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
