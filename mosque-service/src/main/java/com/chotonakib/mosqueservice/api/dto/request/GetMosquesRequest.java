package com.chotonakib.mosqueservice.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMosquesRequest {
    @NotNull
    public double longitude;
    @NotNull
    public double latitude;
    @NotNull
    @Positive
    public double radius;
}
