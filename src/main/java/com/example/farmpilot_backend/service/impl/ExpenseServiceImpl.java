package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.ExpenseDto;
import com.example.farmpilot_backend.dto.ExpenseRequest;
import com.example.farmpilot_backend.entity.Expense;
import com.example.farmpilot_backend.entity.Farm;
import com.example.farmpilot_backend.mapper.ExpenseMapper;
import com.example.farmpilot_backend.repository.ExpenseRepository;
import com.example.farmpilot_backend.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public ExpenseDto createExpense(ExpenseRequest request) {
        Expense expense = expenseMapper.toEntity(request);
        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.toDto(savedExpense);
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with ID: " + id + " not found"));
        return expenseMapper.toDto(expense);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(expenseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ExpenseDto updateExpense(Long id, ExpenseRequest request) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with ID: " + id + " not found"));
        existingExpense.setName(request.getName());
        if (request.getExpense() != null) {
            existingExpense.setExpense(request.getExpense());
        }

        Farm newFarmRef = new Farm();
        newFarmRef.setId(request.getFarmId());
        existingExpense.setFarm(newFarmRef);

        Expense updatedExpense = expenseRepository.save(existingExpense);
        return expenseMapper.toDto(updatedExpense);
    }

    @Override
    @Transactional
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense with ID: " + id + " not found");
        }
        expenseRepository.deleteById(id);
    }
}
