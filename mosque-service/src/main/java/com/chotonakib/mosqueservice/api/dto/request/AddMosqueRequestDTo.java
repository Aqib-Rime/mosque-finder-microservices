package com.chotonakib.mosqueservice.api.dto.request;

import com.chotonakib.mosqueservice.models.enums.MosqueFeatures;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class AddMosqueRequestDTo {
    @NotNull
    private final String name;
    //    private final MosqueStatus mosqueStatus;
    @NotNull
    private final String imageUrl;
    private final String comment;
    @NotNull
    private final String addressString;
    @NotNull
    private final String googleGeneratedAddress;
    @NotNull
    private final Double latitude;
    @NotNull
    private final Double longitude;
    @NotNull
    private final Set<MosqueFeatures> features;
    private final String contributorEmail;
}
