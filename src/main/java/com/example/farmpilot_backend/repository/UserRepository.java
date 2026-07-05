package com.example.farmpilot_backend.repository;

import com.example.farmpilot_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
