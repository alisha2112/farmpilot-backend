package com.example.farmpilot_backend.mapper;

import com.example.farmpilot_backend.dto.PigDto;
import com.example.farmpilot_backend.dto.PigRequest;
import com.example.farmpilot_backend.entity.Pig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PigMapper {
    @Mapping(source = "farm.id", target = "farmId")
    PigDto toDto(Pig pig);

    @Mapping(source = "farmId", target = "farm.id")
    Pig toEntity(PigRequest pigRequest);
}