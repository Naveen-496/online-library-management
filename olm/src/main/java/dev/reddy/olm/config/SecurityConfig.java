package dev.reddy.olm.config;


import dev.reddy.olm.entity.User;
import dev.reddy.olm.security.JwtAuthenticationFilter;
import dev.reddy.olm.security.JwtAuthenticationProvider;
import dev.reddy.olm.security.SkipPathRequestMatcher;
import dev.reddy.olm.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private  JwtService jwtService;



    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(
                new JwtAuthenticationProvider(jwtService)
        );
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter() {
        return new JwtAuthenticationFilter( new SkipPathRequestMatcher(List.of("/api/auth/**"), "/api/**"), authenticationManager());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> request.requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager())
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
