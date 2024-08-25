package com.springsecurity.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.core.Authentication;
import com.springsecurity.springsecurity.models.Users;
import com.springsecurity.springsecurity.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService service;

    @Autowired
    private AuthenticationManager authManager;

    public Users register(@RequestBody Users user) {
        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated())
            return service.generateToken();

        return "Failed";
    }
}
