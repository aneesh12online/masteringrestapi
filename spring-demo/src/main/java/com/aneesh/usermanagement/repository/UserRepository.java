package com.aneesh.usermanagement.repository;

import com.aneesh.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find user by email
    Optional<User> findByEmail(String email);

    List<User> findByName(String name);
}