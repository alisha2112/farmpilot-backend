package com.example.farmpilot_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "feed_consumption")
@Getter
@Setter
@NoArgsConstructor
public class FeedConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount_kg", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountKg;
    @Column(name = "date", nullable = false)
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedInventory feedInventory;
}
