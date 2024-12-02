package com.lcwd.user.service.userservice.services;

import java.util.List;

import com.lcwd.user.service.userservice.model.User;

public interface UserService {

    // user operations

    // create
    User saveUser(User user);

    // get all user
    List<User> getAllUser();

    // get single user of given user
    User getUser(String userId);

}
