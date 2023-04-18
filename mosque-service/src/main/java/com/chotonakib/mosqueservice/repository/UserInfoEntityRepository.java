package com.chotonakib.mosqueservice.repository;

import com.chotonakib.mosqueservice.models.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoEntityRepository extends JpaRepository<UserInfoEntity, UUID> {
    Optional<UserInfoEntity> findByEmailIgnoreCase(String email);

    Optional<UserInfoEntity> findByUserNameIgnoreCase(String username);
}