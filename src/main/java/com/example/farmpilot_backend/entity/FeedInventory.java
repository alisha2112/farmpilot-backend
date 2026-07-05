package com.example.farmpilot_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "feed_inventory")
@Getter
@Setter
@NoArgsConstructor
public class FeedInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "cost_per_kg", nullable = false, precision = 15, scale = 2)
    private BigDecimal costPerKg;
    @Column(name = "number_in_kg", nullable = false, precision = 10, scale = 2)
    private BigDecimal numberInKg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;
}
