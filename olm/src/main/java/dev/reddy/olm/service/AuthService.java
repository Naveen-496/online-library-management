package dev.reddy.olm.service;

import dev.reddy.olm.dto.LoginResponse;
import dev.reddy.olm.dto.UserResponse;

public interface AuthService {

    LoginResponse doLoginUser(String email, String password);
    UserResponse doRegister(String firstName, String lastName, String email, String password, String bio);
}
