package com.lcwd.user.service.userservice.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.userservice.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.userservice.model.Rating;
import com.lcwd.user.service.userservice.model.User;
import com.lcwd.user.service.userservice.repo.UserRepo;
import com.lcwd.user.service.userservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        // get user from db with the help of user repository
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        // fetch rating of the above user from Rating-service
        // http://localhost:8083/ratings/users/7135ef1c-3b65-41a2-b9ce-2627b339c16d
        ArrayList<Rating> forObject = restTemplate.getForObject(
                "http://localhost:8083/ratings/users/7135ef1c-3b65-41a2-b9ce-2627b339c16d", ArrayList.class);
        logger.info("{} ", forObject);

        user.setRatings(forObject);

        return user;
    }

}
