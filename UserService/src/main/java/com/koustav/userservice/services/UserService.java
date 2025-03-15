package com.koustav.userservice.services;

import com.koustav.userservice.entities.User;

import java.util.List;

public interface UserService {
    //User operations
    //create
    User saveUser(User user);
    //get all user
    List<User> getAllUser();
    //get single user of given UserID
    User getUser(String userId);
    //TODO: delete
    //TODO: Update
}
