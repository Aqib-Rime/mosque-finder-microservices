package com.chotonakib.mosqueservice.api.dto.mapper;

import com.chotonakib.mosqueservice.api.dto.request.AddMosqueRequestDTo;
import com.chotonakib.mosqueservice.api.dto.response.MosqueDetailsEntityDto;
import com.chotonakib.mosqueservice.models.entity.AddressEntity;
import com.chotonakib.mosqueservice.models.entity.MosqueDetailsEntity;
import com.chotonakib.mosqueservice.models.entity.UserInfoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MosqueDetailsEntityDtoMapper {
    private final ModelMapper mapper;

    public MosqueDetailsEntityDtoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public MosqueDetailsEntityDto toDto(MosqueDetailsEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, MosqueDetailsEntityDto.class);
    }

    public MosqueDetailsEntity toEntity(AddMosqueRequestDTo addMosqueRequestDTo, UserInfoEntity userInfoEntity) {
        return MosqueDetailsEntity.builder()
                .name(addMosqueRequestDTo.getName())
                .imageUrl(addMosqueRequestDTo.getImageUrl())
                .addressEntity(AddressEntity.builder()
                        .address(addMosqueRequestDTo.getAddressString())
                        .latitude(addMosqueRequestDTo.getLatitude())
                        .longitude(addMosqueRequestDTo.getLongitude())
                        .googleGeneratedAddress(addMosqueRequestDTo.getGoogleGeneratedAddress())
                        .build())
                .features(addMosqueRequestDTo.getFeatures())
                .contributor(userInfoEntity)
                .comment(addMosqueRequestDTo.getComment())
                .build();
    }
}
