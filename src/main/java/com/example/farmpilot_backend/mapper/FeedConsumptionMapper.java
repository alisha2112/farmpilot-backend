package com.example.farmpilot_backend.mapper;

import com.example.farmpilot_backend.dto.FeedConsumptionDto;
import com.example.farmpilot_backend.dto.FeedConsumptionRequest;
import com.example.farmpilot_backend.entity.FeedConsumption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedConsumptionMapper {
    @Mapping(source = "feedInventory.id", target = "feedInventoryId")
    FeedConsumptionDto toDto(FeedConsumption feedConsumption);

    @Mapping(source = "feedInventoryId", target = "feedInventory.id")
    FeedConsumption toEntity(FeedConsumptionRequest feedConsumptionRequest);
}
