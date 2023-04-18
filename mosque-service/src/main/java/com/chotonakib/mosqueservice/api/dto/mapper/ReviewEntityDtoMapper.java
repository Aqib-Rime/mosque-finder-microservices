package com.chotonakib.mosqueservice.api.dto.mapper;


import com.chotonakib.mosqueservice.api.dto.response.ReviewEntityDto;
import com.chotonakib.mosqueservice.models.entity.ReviewEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class ReviewEntityDtoMapper {
    private final ModelMapper mapper;

    public ReviewEntityDto toDto(ReviewEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ReviewEntityDto.class);
    }
}
