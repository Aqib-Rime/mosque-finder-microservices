package com.chotonakib.mosqueservice.api.dto.response;

import com.chotonakib.mosqueservice.models.entity.AddressEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link AddressEntity} entity
 */
@Data
public class AddressEntityDto implements Serializable {
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private String googleGeneratedAddress;
}