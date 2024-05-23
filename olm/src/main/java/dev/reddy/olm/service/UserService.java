package dev.reddy.olm.service;

import dev.reddy.olm.entity.Credentials;
import dev.reddy.olm.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> findUserByEmail(String email);
    User findByEmail(String email);
    Credentials findCredentialsByUserId(Long userId);
    User saveAdmin(User user);
}
