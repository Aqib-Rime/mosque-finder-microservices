package com.chotonakib.mosqueservice.api.dto.mapper;

import com.chotonakib.mosqueservice.api.dto.response.NotificationEntityDto;
import com.chotonakib.mosqueservice.models.entity.NotificationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NotificationEntityDtoMapper {
    private final ModelMapper mapper;

    public NotificationEntityDtoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public NotificationEntityDto toDto(NotificationEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, NotificationEntityDto.class);
    }
}
