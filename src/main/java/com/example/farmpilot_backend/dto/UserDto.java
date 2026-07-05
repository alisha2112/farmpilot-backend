package com.example.farmpilot_backend.dto;

import com.example.farmpilot_backend.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private Long farmId;
}