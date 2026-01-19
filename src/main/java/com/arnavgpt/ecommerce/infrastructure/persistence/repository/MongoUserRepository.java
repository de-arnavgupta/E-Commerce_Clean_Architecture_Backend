package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.domain.entity.User;
import com.arnavgpt.ecommerce.domain.repository.UserRepository;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.UserDocument;
import com.arnavgpt.ecommerce.infrastructure.persistence.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataRepository;

    public MongoUserRepository(SpringDataUserRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public User save(User user) {
        UserDocument document = UserMapper.toDocument(user);
        UserDocument savedDocument = springDataRepository.save(document);
        return UserMapper.toDomain(savedDocument);
    }

    @Override
    public Optional<User> findById(String id) {
        return springDataRepository.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsById(String id) {
        return springDataRepository.existsById(id);
    }
}