package com.chotonakib.mosqueservice.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "review")
@ToString
@RequiredArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mosque_details_entity_id")
    private MosqueDetailsEntity mosqueDetailsEntity;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "message", nullable = false, length = 5000)
    private String message;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private UserInfoEntity reviewer;

    @Column(name = "review_time", nullable = false)
    private LocalDateTime reviewTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReviewEntity that = (ReviewEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}