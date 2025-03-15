package com.koustav.rating.services;

import com.koustav.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RatingService {

    //create
    Rating create(Rating rating);
    //get all ratings
    List<Rating> getRatings();
    //get all by User Id
    List<Rating> getRatingByUserId(String userId);
    //get all by Hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
