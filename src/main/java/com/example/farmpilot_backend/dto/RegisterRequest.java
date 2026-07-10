package com.example.farmpilot_backend.dto;

import com.example.farmpilot_backend.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required (FARMER or VETERINARIAN)")
    private Role role;

    @NotNull(message = "Farm ID is required")
    private Long farmId;

    private String firstName;
    private String lastName;
    private String phone;
}