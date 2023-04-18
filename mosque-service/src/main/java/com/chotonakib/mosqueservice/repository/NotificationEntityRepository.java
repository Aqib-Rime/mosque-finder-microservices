package com.chotonakib.mosqueservice.repository;

import com.chotonakib.mosqueservice.models.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationEntityRepository extends JpaRepository<NotificationEntity, UUID> {

}