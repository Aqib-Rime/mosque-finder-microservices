package com.chotonakib.mosqueservice.models.entity;

import com.chotonakib.mosqueservice.models.enums.Badge;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_info")
@ToString
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_display_name", nullable = false)
    private String userDisplayName;

    @Column(name = "user_photo_url", length = 2000)
    private String userPhotoUrl;

    @Column(name = "user_award_point", nullable = false)
    private Integer userAwardPoint;

    @Column(name = "email", nullable = false, unique = true, length = 320)
    @Email
    private String email;

    @Enumerated
    @Column(name = "badge", nullable = false)
    private Badge badge;

    @OneToMany(mappedBy = "contributor", orphanRemoval = true)
    @ToString.Exclude
    private Set<MosqueDetailsEntity> contributions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "reviewer", orphanRemoval = true)
    @ToString.Exclude
    private Set<ReviewEntity> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userInfoEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<NotificationEntity> notificationEntities = new LinkedHashSet<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserInfoEntity that = (UserInfoEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}