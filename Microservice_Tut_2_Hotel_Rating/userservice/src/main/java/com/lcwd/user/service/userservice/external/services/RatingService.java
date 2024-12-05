package com.lcwd.user.service.userservice.external.services;

// import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.userservice.model.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    // get

    // post
    @PostMapping("/ratings")
    // public Rating createRating(Map<String,Object> values); // Use when Rating is
    // not defined
    public Rating createRating(Rating values);

    // put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    // delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);

}
