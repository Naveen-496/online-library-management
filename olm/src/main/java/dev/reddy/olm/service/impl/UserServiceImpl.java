package dev.reddy.olm.service.impl;

import dev.reddy.olm.dto.AdminUserRequest;
import dev.reddy.olm.entity.Credentials;
import dev.reddy.olm.entity.Role;
import dev.reddy.olm.entity.User;
import dev.reddy.olm.exception.ApiException;
import dev.reddy.olm.exception.InvalidCredentialsException;
import dev.reddy.olm.repository.CredentialRepository;
import dev.reddy.olm.repository.RoleRepository;
import dev.reddy.olm.repository.UserRepository;
import dev.reddy.olm.service.UserService;
import dev.reddy.olm.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dev.reddy.olm.util.AuthUtils.generateRandomPassword;
import static dev.reddy.olm.util.UserUtil.createCredentials;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public User saveAdmin(AdminUserRequest userRequest) {
        var user = UserUtil.toUser(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getBio());
        var role = getRoleByName(userRequest.getRole());
        user.setRole(role);
        var randomPassword = generateRandomPassword();
        var credentials = createCredentials(passwordEncoder.encode(randomPassword), user);
        credentialRepository.save(credentials);
        return userRepository.save(user);
    }

    private Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() -> new ApiException("Role not found : " + roleName));
    }
}
