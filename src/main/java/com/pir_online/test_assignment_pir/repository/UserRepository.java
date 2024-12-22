package com.pir_online.test_assignment_pir.repository;

import com.pir_online.test_assignment_pir.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String login);
}
