package com.arnavgpt.ecommerce.domain.repository;

import com.arnavgpt.ecommerce.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    boolean existsById(String id);
}