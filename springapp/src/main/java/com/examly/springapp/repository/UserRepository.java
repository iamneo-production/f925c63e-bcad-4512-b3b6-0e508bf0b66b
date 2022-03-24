package com.examly.springapp.repository;

import com.examly.springapp.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
  Iterable<User> findAllByRole(String role);
}
