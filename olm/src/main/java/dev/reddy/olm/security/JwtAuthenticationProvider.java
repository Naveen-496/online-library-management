package dev.reddy.olm.security;

import dev.reddy.olm.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var accessToken = (String) authentication.getCredentials();
        var securityUser = jwtService.extractUser(accessToken);
        var authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(securityUser.getAuthority());
        return JwtAuthentication.authenticated(securityUser, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }
}
