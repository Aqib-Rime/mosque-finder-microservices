package com.chotonakib.mosqueservice.models.entity;

import com.chotonakib.mosqueservice.models.enums.MosqueFeatures;
import com.chotonakib.mosqueservice.models.enums.MosqueStatus;
import jakarta.persistence.*;
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
@Table(name = "mosque_details")
@ToString
public class MosqueDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 5000)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "mosque_status", nullable = false, length = 30)
    private MosqueStatus mosqueStatus;

    @Column(name = "image_url", nullable = false, unique = true, length = 2000)
    private String imageUrl;

    @Column(name = "comment", length = 5000)
    private String comment;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_entity_id", nullable = false, unique = true)
    private AddressEntity addressEntity;

    @ElementCollection
    @Column(name = "features", nullable = false)
    @CollectionTable(name = "mosque_details_features", joinColumns = @JoinColumn(name = "owner_id"))
    private Set<MosqueFeatures> features = new LinkedHashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "contributor_id", nullable = false)
    private UserInfoEntity contributor;

    @OneToMany(mappedBy = "mosqueDetailsEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<ReviewEntity> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "mosqueDetailsEntity", orphanRemoval = true)
    @ToString.Exclude
    private Set<NotificationEntity> notifications = new LinkedHashSet<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MosqueDetailsEntity entity = (MosqueDetailsEntity) o;
        return getId() != null && Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}