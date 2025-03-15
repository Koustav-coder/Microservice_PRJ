package com.koustav.hotelservice.HotelService.services;

import com.koustav.hotelservice.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    //Hotel operations
    //create
    Hotel create(Hotel hotel);
    //get all hotels
    List<Hotel> getAll();
    //get single hotel with help of id
    Hotel get(String id);
}
