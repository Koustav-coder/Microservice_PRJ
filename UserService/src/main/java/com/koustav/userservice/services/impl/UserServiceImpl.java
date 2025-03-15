package com.koustav.userservice.services.impl;

import com.koustav.userservice.entities.Hotel;
import com.koustav.userservice.entities.Rating;
import com.koustav.userservice.entities.User;
import com.koustav.userservice.exceptions.ResourceNotFoundException;
import com.koustav.userservice.repositoriies.UserRepository;
import com.koustav.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
    }

    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

//    @Override
//    public List<User> getAllUser() {
//        return userRepository.findAll();
//    }
@Override
public List<User> getAllUser() {
    List<User> users = userRepository.findAll();

    // Iterate over each user and fetch their ratings
    users.forEach(user -> {
        // Fetch ratings from Rating Service
        String ratingServiceUrl = "http://localhost:8083/ratings/users/" + user.getUserId();
        Rating[] ratingsOfUser = restTemplate.getForObject(ratingServiceUrl, Rating[].class);

        logger.info("Ratings for user {}: {}", user.getUserId(), ratingsOfUser);

        if (ratingsOfUser != null) {
            List<Rating> ratingList = Arrays.stream(ratingsOfUser).peek(rating -> {
                // Fetch hotel details from Hotel Service
                String hotelServiceUrl = "http://localhost:8082/hotels/" + rating.getHotelId();
                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(hotelServiceUrl, Hotel.class);
                Hotel hotel = forEntity.getBody();

                logger.info("Hotel response status code for user {}: {}", user.getUserId(), forEntity.getStatusCode());

                // Set the hotel object to rating
                rating.setHotel(hotel);
            }).collect(Collectors.toList());

            // Set ratings with hotels for the user
            user.setRatings(ratingList);
        } else {
            user.setRatings(new ArrayList<>()); // Assign empty list if no ratings
        }
    });

    return users;
}

    @Override
    public User getUser(String userId) {
        //get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User with given id is not found on server !! : "+ userId));
        //fetch rating of the above user from Rating Service
        //http://localhost:8083/ratings/users/{userId}
        Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}", (Object) ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().peek(rating -> {
            //api call to hotel service to get the hotel
           // http://localhost:8082/hotels/99b67f58-4f72-4283-92ad-40369285360b
            ResponseEntity<Hotel> forEntity =restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: {}",forEntity.getStatusCode());
            //set  the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            //return rating;   -----> we need not to return now, as we are using stream().peek() instead of stream.map()
        }).collect(Collectors.toList());


        user.setRatings(ratingList);
        return user;
    }
}
