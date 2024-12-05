package com.lcwd.user.service.userservice.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.userservice.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.userservice.model.Hotel;
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
        Rating[] forObject = restTemplate.getForObject(
                "http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", forObject);

        List<Rating> ratings = Arrays.stream(forObject).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            // http://localhost:8082/hotels/5802b477-7c49-48f8-8db1-42f21fe9e3d0
            ResponseEntity<Hotel> forHotel = restTemplate
                    .getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forHotel.getBody();
            logger.info("response status code: ", forHotel.getStatusCode());

            // set the hotel to rating
            rating.setHotel(hotel);
            // return rating;
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

}
