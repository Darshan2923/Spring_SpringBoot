package com.lcwd.user.service.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.user.service.userservice.external.services.RatingService;
import com.lcwd.user.service.userservice.model.Rating;

@SpringBootTest
class UserserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is build using feign client")
				.build();
		Rating savedRating = ratingService.createRating(rating);

		System.out.println("new rating created: " + savedRating);
	}

}
