package com.portifolyo.airlinesystem.repository;

import com.portifolyo.airlinesystem.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
