package com.chotonakib.mosqueservice.api.dto.mapper;
import com.chotonakib.mosqueservice.api.dto.response.UserInfoEntityDto;
import com.chotonakib.mosqueservice.models.entity.UserInfoEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserInfoEntityMapper {
    private final ModelMapper mapper;

    public UserInfoEntityDto toDto(UserInfoEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserInfoEntityDto.class);
    }

    public UserInfoEntity toEntity(UserInfoEntityDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserInfoEntity.class);
    }
}
