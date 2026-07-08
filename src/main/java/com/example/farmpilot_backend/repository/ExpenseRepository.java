package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT COALESCE(SUM(e.expense), 0) FROM Expense e WHERE EXTRACT(MONTH FROM e.expenseDate) = :month " +
            "AND EXTRACT(YEAR FROM e.expenseDate) = :year")
    BigDecimal sumExpensesByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT category FROM expenses WHERE EXTRACT(MONTH FROM expense_date) = :month AND " +
            "EXTRACT(YEAR FROM expense_date) = :year GROUP BY category ORDER BY COUNT(id) DESC LIMIT 1", nativeQuery = true)
    String findTopCategoryByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
