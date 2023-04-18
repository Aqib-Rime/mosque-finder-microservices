package com.chotonakib.mosqueservice.repository;

import com.chotonakib.mosqueservice.models.entity.MosqueDetailsEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MosqueDetailsEntityRepository extends JpaRepository<MosqueDetailsEntity, UUID> {
    List<MosqueDetailsEntity> findByAddressEntityIdIn(List<Long> addressIds);

    Optional<MosqueDetailsEntity> findByNameIgnoreCase(@NotNull String name);
}