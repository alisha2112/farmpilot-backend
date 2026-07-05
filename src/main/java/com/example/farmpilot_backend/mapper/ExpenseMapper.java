package com.example.farmpilot_backend.mapper;

import com.example.farmpilot_backend.dto.ExpenseDto;
import com.example.farmpilot_backend.dto.ExpenseRequest;
import com.example.farmpilot_backend.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {
    @Mapping(source = "farm.id", target = "farmId")
    ExpenseDto toDto(Expense expense);

    @Mapping(source = "farmId", target = "farm.id")
    Expense toEntity(ExpenseRequest expenseRequest);
}
