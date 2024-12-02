package com.lcwd.user.service.userservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.user.service.userservice.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.userservice.model.User;
import com.lcwd.user.service.userservice.repo.UserRepo;
import com.lcwd.user.service.userservice.services.UserService;

@Service
public class UserSerbiveImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
    }

}
