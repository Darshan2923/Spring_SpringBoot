package com.lcwd.user.service.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.user.service.userservice.model.User;

public interface UserRepo extends JpaRepository<User, String> {

    // cutom queries here

}
