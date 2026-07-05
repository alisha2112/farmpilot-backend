package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Pig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PigRepository extends JpaRepository<Pig, Long> {
}
