package com.example.farmpilot_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedInventoryRequest {
    private String name;
    private Long farmId;
}
