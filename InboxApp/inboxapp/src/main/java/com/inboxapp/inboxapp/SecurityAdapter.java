package com.inboxapp.inboxapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import static org.springframework.security.config.Customizer.withDefaults; // Ensure this is imported

@Configuration
public class SecurityAdapter {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Authorize requests
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/error", "/user").permitAll() // Public endpoints
                        .anyRequest().authenticated() // Secure other endpoints
                )
                // Exception handling
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                // CSRF configuration
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                // Logout configuration
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll())
                // OAuth2 login configuration
                .oauth2Login(withDefaults()); // Apply default configurations for OAuth2 login

        return http.build();
    }
}
