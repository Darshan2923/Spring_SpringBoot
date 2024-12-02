package com.lcwd.rating.ratingservice.services;

import java.util.List;

import com.lcwd.rating.ratingservice.models.Rating;

public interface RatingService {

    // create
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getRatings();

    // get all bu UserId
    List<Rating> getByUserId(String userId);

    // get all by hotel
    List<Rating> getByHotelId(String hotelId);
}
