package com.chotonakib.mosqueservice.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMosqueRequestDto {
    @NotNull
    private final String name;
    private final String updatedName;
    private final String mosqueStatus;
    private final String imageUrl;
    private final String comment;
    private final String addressString;
    private final Double latitude;
    private final Double longitude;
}
