package com.koustav.rating.repository;

import com.koustav.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository <Rating,String>{
    //custom finder methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
