package com.example.farmpilot_backend.mapper;

import com.example.farmpilot_backend.dto.FarmDto;
import com.example.farmpilot_backend.dto.FarmRequest;
import com.example.farmpilot_backend.entity.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmDto toDto(Farm farm);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Farm toEntity(FarmRequest farmRequest);
}
