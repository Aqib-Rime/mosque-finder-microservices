package com.chotonakib.mosqueservice.api.dto.mapper;

import com.chotonakib.mosqueservice.api.dto.response.AddressEntityDto;
import com.chotonakib.mosqueservice.models.entity.AddressEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AddressEntityDtoMapper {
    private final ModelMapper modelMapper;

    public AddressEntityDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AddressEntityDto toDto(AddressEntity entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, AddressEntityDto.class);
    }
}
