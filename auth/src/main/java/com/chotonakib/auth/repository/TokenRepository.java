package com.chotonakib.auth.repository;

import com.chotonakib.auth.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

  @Query(value = """
          select t from Token t inner join UserInfoEntity u\s
          on t.userInfoEntity.id = u.id\s
          where u.id = :id and (t.expired = false or t.revoked = false)\s
          """)
  List<Token> findAllValidTokenByUser(Long id);

  Optional<Token> findByToken(String token);
}
