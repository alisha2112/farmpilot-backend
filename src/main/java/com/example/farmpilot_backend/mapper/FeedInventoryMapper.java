package com.example.farmpilot_backend.mapper;

import com.example.farmpilot_backend.dto.FeedInventoryDto;
import com.example.farmpilot_backend.dto.FeedInventoryRequest;
import com.example.farmpilot_backend.entity.FeedInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedInventoryMapper {
    @Mapping(source = "farm.id", target = "farmId")
    FeedInventoryDto toDto(FeedInventory feedInventory);

    @Mapping(source = "farmId", target = "farm.id")
    FeedInventory toEntity(FeedInventoryRequest feedInventoryRequest);
}
