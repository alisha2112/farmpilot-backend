package com.example.farmpilot_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
@Getter
@Setter
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String source;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "income_date", nullable = false)
    private LocalDate incomeDate;

    @Column(nullable = false, length = 50)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pig_id")
    private Pig pig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @PrePersist
    public void prePersist() {
        if (this.incomeDate == null) {
            this.incomeDate = LocalDate.now();
        }
        if (this.category == null) {
            this.category = "PIG_SALE";
        }
    }
}