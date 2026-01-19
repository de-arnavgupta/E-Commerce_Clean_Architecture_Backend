package com.arnavgpt.ecommerce.application.service;

import com.arnavgpt.ecommerce.application.dto.CreateUserRequest;
import com.arnavgpt.ecommerce.application.dto.UserResponse;
import com.arnavgpt.ecommerce.domain.entity.User;
import com.arnavgpt.ecommerce.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = new User(
                null,
                request.username(),
                request.email(),
                request.role()
        );

        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        return toResponse(user);
    }

    public boolean userExists(String id) {
        return userRepository.existsById(id);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}