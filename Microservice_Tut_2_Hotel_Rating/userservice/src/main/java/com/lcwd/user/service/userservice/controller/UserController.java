package com.lcwd.user.service.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.userservice.model.User;
import com.lcwd.user.service.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount = 1;

    // single user get
    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod =
    // "ratingHotelFallBack")
    // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        User user2 = userService.getUser(userId);
        return ResponseEntity.ok(user2);
    }

    // fallback method for circuitMethod
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        // logger.info("Fallback is executed beacuse service is down: ",
        // ex.getMessage());
        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("Some service is down so dummy")
                .userId("14215").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // get all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

}
