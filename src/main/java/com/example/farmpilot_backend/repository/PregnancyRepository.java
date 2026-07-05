package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.Pregnancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PregnancyRepository extends JpaRepository<Pregnancy, Long> {
}
