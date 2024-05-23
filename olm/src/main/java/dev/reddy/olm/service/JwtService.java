package dev.reddy.olm.service;

import dev.reddy.olm.dto.UserResponse;
import dev.reddy.olm.entity.User;
import dev.reddy.olm.model.SecurityUser;

public interface JwtService {

    String createToken(User user);
    SecurityUser extractUser(String accessToken);
}
