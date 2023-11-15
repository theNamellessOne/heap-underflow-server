package com.example.heapunderflowserver.repository;

import com.example.heapunderflowserver.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User> {
    Optional<User> findByEmail(String email);
}
