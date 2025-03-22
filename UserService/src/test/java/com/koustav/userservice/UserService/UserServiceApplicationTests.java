package com.koustav.userservice.UserService;

import com.koustav.userservice.entities.Rating;
import com.koustav.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
    private RatingService ratingService;
    @Test
	void createRating(){
		Rating rating = Rating.builder().rating(5).userId("").hotelId("").feedback("this is created using feign client 2nd").build();
		ResponseEntity<Rating> responseEntity = ratingService.createRating(rating);
		responseEntity.getStatusCode();
		System.out.println("new rating created");
	}

}
