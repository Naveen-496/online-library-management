package dev.reddy.olm.controller;


import dev.reddy.olm.dto.ApiResponse;
import dev.reddy.olm.dto.LoginRequest;
import dev.reddy.olm.dto.LoginResponse;
import dev.reddy.olm.dto.UserRegisterRequest;
import dev.reddy.olm.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.reddy.olm.dto.ApiResponse.getResponse;
import static dev.reddy.olm.dto.ApiResponse.getSuccessResponse;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse loginUser(@RequestBody LoginRequest loginRequest) {
       log.info("Received Login Request: {}", loginRequest);
       return getSuccessResponse(authService.doLoginUser(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody UserRegisterRequest registerRequest) {
        var response =  authService.doRegister(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getBio());
        return getSuccessResponse(response);
    }
}
