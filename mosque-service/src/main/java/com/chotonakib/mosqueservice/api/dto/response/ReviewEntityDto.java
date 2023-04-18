package com.chotonakib.mosqueservice.api.dto.response;

import com.chotonakib.mosqueservice.models.entity.ReviewEntity;
import com.chotonakib.mosqueservice.models.enums.Badge;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link ReviewEntity} entity
 */
@Data
public class ReviewEntityDto implements Serializable {
    private final Double rating;
    private final String message;
    private final String reviewerUserName;
    private final String reviewerUserDisplayName;
    private final String reviewerUserPhotoUrl;
    private final Integer reviewerUserAwardPoint;
    private final String reviewerEmail;
    private final Badge reviewerBadge;
    private final LocalDateTime reviewTime;
}