package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.infrastructure.persistence.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmail(String email);
}