package com.example.farmpilot_backend.mapper;

import com.example.farmpilot_backend.dto.PregnancyDto;
import com.example.farmpilot_backend.dto.PregnancyRequest;
import com.example.farmpilot_backend.entity.Pregnancy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PregnancyMapper {
    @Mapping(source = "pig.id", target = "pigId")
    PregnancyDto toDto(Pregnancy pregnancy);

    @Mapping(source = "pigId", target = "pig.id")
    Pregnancy toEntity(PregnancyRequest pregnancyRequest);
}
