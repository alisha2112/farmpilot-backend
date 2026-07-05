package com.example.farmpilot_backend.entity;

import com.example.farmpilot_backend.entity.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "expense", nullable = false, precision = 15, scale = 2)
    private BigDecimal expense;
    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 50)
    private ExpenseCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;
}
