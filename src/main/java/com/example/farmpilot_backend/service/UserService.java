package com.example.farmpilot_backend.service;

import com.example.farmpilot_backend.dto.UserDto;
import com.example.farmpilot_backend.dto.UserRequest;

import java.util.List;

public interface UserService {
    UserDto createUser(UserRequest request);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
}
