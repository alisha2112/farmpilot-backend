package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.ExpenseDto;
import com.example.farmpilot_backend.dto.ExpenseRequest;

import java.util.List;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseRequest request);
    ExpenseDto getExpenseById(Long id);
    List<ExpenseDto> getAllExpenses();
    ExpenseDto updateExpense(Long id, ExpenseRequest request);
    void deleteExpense(Long id);
}
