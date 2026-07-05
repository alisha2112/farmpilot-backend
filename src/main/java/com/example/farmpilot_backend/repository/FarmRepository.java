package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
