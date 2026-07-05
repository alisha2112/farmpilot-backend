package com.example.farmpilot_backend.entity;

import com.example.farmpilot_backend.entity.enums.PregnancyStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pregnancies")
@Getter
@Setter
@NoArgsConstructor
public class Pregnancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "insemination_date", nullable = false)
    private LocalDate inseminationDate = LocalDate.now();
    @Column(name = "expected_birth_date", nullable = false)
    private LocalDate expectedBirthDate;
    @Column(name = "actual_birth_date")
    private LocalDate actualBirthDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PregnancyStatus status = PregnancyStatus.PLANNED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pig_id", nullable = false)
    private Pig pig;

    @PrePersist
    public void calculateExpectedBirthDate() {
        if (this.inseminationDate != null && this.expectedBirthDate == null) {
            this.expectedBirthDate = this.inseminationDate.plusDays(114);
        }
    }
}
