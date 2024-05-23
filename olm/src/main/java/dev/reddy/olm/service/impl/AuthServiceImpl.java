package dev.reddy.olm.service.impl;

import dev.reddy.olm.dto.LoginResponse;
import dev.reddy.olm.dto.UserResponse;
import dev.reddy.olm.entity.Role;
import dev.reddy.olm.enums.Authority;
import dev.reddy.olm.exception.ApiException;
import dev.reddy.olm.exception.InvalidCredentialsException;
import dev.reddy.olm.service.*;
import dev.reddy.olm.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final CredentialsService credentialsService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Autowired
    public AuthServiceImpl(UserService userService, CredentialsService credentialsService, RoleService roleService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.credentialsService = credentialsService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse doLoginUser(String email, String password) {
        var user = userService.findByEmail(email);
        var credentials = userService.findCredentialsByUserId(user.getId());
        authenticate(password, credentials.getPassword());
        var accessToken = jwtService.createToken(user);
        var refreshToken = jwtService.createToken(user);
        return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    private void authenticate(String rawPassword, String password) {
        if (passwordEncoder.matches(rawPassword, password)) return;
        throw new InvalidCredentialsException("Incorrect password");
    }

    @Override
    public UserResponse doRegister(String firstName, String lastName, String email, String password, String bio) {
       var user = UserUtil.toUser(firstName, lastName, email, bio);
       var role = getRoleByName("User");
       user.setRole(role);
       var credentials = UserUtil.createCredentials(passwordEncoder.encode(password), user);
       userService.saveUser(user);
       credentialsService.saveCredentials(credentials);
       return UserUtil.toUserResponse(user);
    }

    private Role getRoleByName(String name) {
        return roleService.findRoleByName(name).orElseThrow(() -> new ApiException("Role not found: " + name));
    }
}
