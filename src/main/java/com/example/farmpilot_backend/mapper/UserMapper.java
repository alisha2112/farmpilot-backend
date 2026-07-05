package com.example.farmpilot_backend.mapper;

import com.example.farmpilot_backend.dto.UserDto;
import com.example.farmpilot_backend.dto.UserRequest;
import com.example.farmpilot_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "farm.id", target = "farmId")
    UserDto toDto(User user);

    @Mapping(source = "farmId", target = "farm.id")
    User toEntity(UserRequest request);
}