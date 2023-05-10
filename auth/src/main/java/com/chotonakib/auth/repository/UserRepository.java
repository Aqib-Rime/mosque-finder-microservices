package com.chotonakib.auth.repository;

import com.chotonakib.auth.model.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByEmailIgnoreCase(@NonNull String email);

}
