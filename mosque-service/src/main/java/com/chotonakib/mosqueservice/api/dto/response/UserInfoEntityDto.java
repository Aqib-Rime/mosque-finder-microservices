package com.chotonakib.mosqueservice.api.dto.response;

import com.chotonakib.mosqueservice.models.entity.UserInfoEntity;
import com.chotonakib.mosqueservice.models.enums.Badge;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserInfoEntity} entity
 */
@Data
public class UserInfoEntityDto implements Serializable {
    @NotNull
    private final String userName;
    private final String userDisplayName;
    private final String userPhotoUrl;
    private final Integer userAwardPoint;
    @NotNull
    private final String email;
    private final Badge badge;
}