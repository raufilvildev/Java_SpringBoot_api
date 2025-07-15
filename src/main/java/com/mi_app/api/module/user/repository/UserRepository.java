package com.mi_app.api.module.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mi_app.api.module.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUuid(String uuid);

  Optional<User> findByUsername(String username);

  boolean existsByUuid(String uuid);

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

  void deleteByUuid(String uuid);
}
