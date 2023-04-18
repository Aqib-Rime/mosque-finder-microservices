package com.chotonakib.mosqueservice.api.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {
    @NotNull
    private String id;
    private Double latitude;
    private Double longitude;
    private Double radius;
}
