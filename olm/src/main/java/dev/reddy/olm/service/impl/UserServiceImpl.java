package dev.reddy.olm.service.impl;

import dev.reddy.olm.entity.Credentials;
import dev.reddy.olm.entity.User;
import dev.reddy.olm.exception.ApiException;
import dev.reddy.olm.exception.InvalidCredentialsException;
import dev.reddy.olm.repository.CredentialRepository;
import dev.reddy.olm.repository.UserRepository;
import dev.reddy.olm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        var user = findByEmail(email);
        return Optional.empty();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new InvalidCredentialsException("Email Id not present"));
    }

    @Override
    public Credentials findCredentialsByUserId(Long userId) {
        return credentialRepository.findByUserId(userId).orElseThrow(() -> new ApiException("Credentials not found"));
    }

    @Override
    public User saveAdmin(User user) {
        return null;
    }
}
