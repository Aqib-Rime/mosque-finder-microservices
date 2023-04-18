package com.chotonakib.mosqueservice.api.dto.response;

import com.chotonakib.mosqueservice.models.entity.MosqueDetailsEntity;
import com.chotonakib.mosqueservice.models.entity.ReviewEntity;
import com.chotonakib.mosqueservice.models.enums.Badge;
import com.chotonakib.mosqueservice.models.enums.MosqueFeatures;
import com.chotonakib.mosqueservice.models.enums.MosqueStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link MosqueDetailsEntity} entity
 */
@Data
public class MosqueDetailsEntityDto implements Serializable {
    private final String name;
    private final MosqueStatus mosqueStatus;
    private final String imageUrl;
    private final String comment;
    private final String addressEntityAddress;
    private final Double addressEntityLatitude;
    private final Double addressEntityLongitude;
    @NotNull
    private final Set<MosqueFeatures> features;
    private final String contributorUserDisplayName;
    private final String contributorUserPhotoUrl;
    private final Integer contributorUserAwardPoint;
    private final Badge contributorBadge;
    private final Set<ReviewDto> reviews;

    /**
     * A DTO for the {@link ReviewEntity} entity
     */
    @Data
    public static class ReviewDto implements Serializable {
        private final Double rating;
        private final String message;
        private final String reviewerUserDisplayName;
        private final LocalDateTime reviewTime;
    }
}