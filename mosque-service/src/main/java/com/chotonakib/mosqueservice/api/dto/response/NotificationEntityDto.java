package com.chotonakib.mosqueservice.api.dto.response;

import com.chotonakib.mosqueservice.models.entity.NotificationEntity;
import com.chotonakib.mosqueservice.models.enums.Badge;
import com.chotonakib.mosqueservice.models.enums.MosqueStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link NotificationEntity} entity
 */
@Data
public class NotificationEntityDto implements Serializable {
    private final String title;
    private final String description;
    private final LocalDateTime dateTime;
    private final String mosqueDetailsEntityName;
    private final MosqueStatus mosqueDetailsEntityMosqueStatus;
    private final Boolean isDelivered;
    private final String userInfoEntityUserDisplayName;
    private final String userInfoEntityUserPhotoUrl;
    private final Badge userInfoEntityBadge;
}