package com.lcwd.rating.ratingservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcwd.rating.ratingservice.models.Rating;

@Repository
public interface RatingRepo extends JpaRepository<Rating, String> {

    // custom finder methods
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
