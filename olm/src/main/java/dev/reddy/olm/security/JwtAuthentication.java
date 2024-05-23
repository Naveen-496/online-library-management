package dev.reddy.olm.security;

import dev.reddy.olm.model.SecurityUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private String accessToken;
    private SecurityUser securityUser;
    private boolean isAuthenticated;


    private JwtAuthentication(String accessToken) {
        super(null);
        this.accessToken = accessToken;
        this.isAuthenticated = false;
    }

    private JwtAuthentication(SecurityUser securityUser, Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.accessToken = null;
        this.securityUser = securityUser;
        this.isAuthenticated = true;
    }

    public static JwtAuthentication unAuthenticated(String accessToken) {
        return new JwtAuthentication(accessToken);
    }

    public static JwtAuthentication authenticated(SecurityUser securityUser,  Collection<GrantedAuthority> authorities) {
        return new JwtAuthentication(securityUser, authorities);
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }

    @Override
    public Object getPrincipal() {
        return securityUser;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
