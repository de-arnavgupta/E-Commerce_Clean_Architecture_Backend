package com.arnavgpt.ecommerce.infrastructure.persistence.mapper;

import com.arnavgpt.ecommerce.domain.entity.User;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.UserDocument;

public class UserMapper {

    public static User toDomain(UserDocument document) {
        if (document == null) {
            return null;
        }

        return new User(
                document.getId(),
                document.getUsername(),
                document.getEmail(),
                document.getRole()
        );
    }

    public static UserDocument toDocument(User user) {
        if (user == null) {
            return null;
        }

        return new UserDocument(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}