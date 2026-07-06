package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.dto.UserDto;
import com.example.farmpilot_backend.dto.UserRequest;
import com.example.farmpilot_backend.entity.Farm;
import com.example.farmpilot_backend.entity.User;
import com.example.farmpilot_backend.mapper.UserMapper;
import com.example.farmpilot_backend.repository.UserRepository;
import com.example.farmpilot_backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPasswordHash(hashedPassword);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + id + " not found"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + id + " not found"));
        existingUser.setUsername(request.getUsername());

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        existingUser.setPasswordHash(hashedPassword);

        Farm newFarmRef = new Farm();
        newFarmRef.setId(request.getFarmId());
        existingUser.setFarm(newFarmRef);

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID: " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
