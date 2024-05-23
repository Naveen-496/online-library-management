package dev.reddy.olm.service.impl;

import dev.reddy.olm.dto.UserResponse;
import dev.reddy.olm.entity.User;
import dev.reddy.olm.enums.Authority;
import dev.reddy.olm.model.SecurityUser;
import dev.reddy.olm.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;


@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    private final SecretKey key = Jwts.SIG.HS512.key().build();
    private final Supplier<SecretKey> secretKeyBuilder = () -> Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));


    @Override
    public SecurityUser extractUser(String accessToken) {
        return parseToken(accessToken);
    }

    private final Function<User, String> tokenBuilderFunction = (user) -> Jwts.builder().header()
            .add(Map.of("type", "jwt")).and()
            .audience().add("Reddy LLC").and().id(UUID.randomUUID().toString())
            .issuedAt(new Date())
            .signWith(secretKeyBuilder.get())
            .subject(user.getEmail())
            .claim("role", user.getRole().getName())
            .claim("firstName", user.getFirstName())
            .claim("lastName", user.getLastName())
            .claim("id", user.getId())
            .claim("userId", user.getUserId())
            .claim("authority", user.getRole().getAuthority().getValue())
            .expiration(Date.from(Instant.now().plusSeconds(900)))
            .compact();

    public String createToken(User user) {
        return tokenBuilderFunction.apply(user);
    }

    public SecurityUser parseToken(String token) {
        SecurityUser securityUser = new SecurityUser();
        Claims claims = Jwts.parser().verifyWith(secretKeyBuilder.get()).build().parseSignedClaims(token).getPayload();
        String subject = claims.getSubject();
        String role = (String) claims.get("role");
        Long id = claims.get("id", Long.class);
        String userId = claims.get("userId", String.class);
        String authority = (claims.get("authority", String.class));
        String firstName = claims.get("firstName", String.class);
        String lastName = claims.get("lastName", String.class);
        securityUser.setEmail(subject);
        securityUser.setId(id);
        securityUser.setFirstName(firstName);
        securityUser.setLastName(lastName);
        securityUser.setUserId(userId);
        securityUser.setRole(role);
        securityUser.setAuthority(authority);
        return securityUser;
    }
}
