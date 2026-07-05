package com.example.farmpilot_backend.entity;

import com.example.farmpilot_backend.entity.enums.PigStatus;
import com.example.farmpilot_backend.entity.enums.Sex;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pigs")
@Getter
@Setter
@NoArgsConstructor
public class Pig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tag_number", nullable = false, length = 50)
    private String tagNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", length = 6)
    private Sex sex;
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth = LocalDate.now();;
    @Column(name = "castration", nullable = false)
    private Boolean castration;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "health", columnDefinition = "jsonb")
    private HealthRecord health;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PigStatus status = PigStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;
}
