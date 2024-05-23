package dev.reddy.olm.util;

import dev.reddy.olm.dto.UserResponse;
import dev.reddy.olm.entity.Credentials;
import dev.reddy.olm.entity.User;

import java.util.UUID;

public class UserUtil {

    public static User toUser(String firstName, String lastName, String email, String bio) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .bio(bio)
                .enabled(false)
                .userId(UUID.randomUUID().toString())
                .build();
    }

    public static Credentials createCredentials(String password, User user) {
        return Credentials.builder()
                .password(password)
                .user(user)
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .id(user.getId())
                .phone(user.getPhone())
                .email(user.getEmail())
                .bio(user.getBio())
                .imageUrl(user.getImageUrl())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
