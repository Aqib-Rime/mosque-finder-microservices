package com.chotonakib.mosqueservice.repository;

import com.chotonakib.mosqueservice.models.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressEntityRepository extends JpaRepository<AddressEntity, UUID> {
}