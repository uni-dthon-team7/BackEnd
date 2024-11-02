package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
}