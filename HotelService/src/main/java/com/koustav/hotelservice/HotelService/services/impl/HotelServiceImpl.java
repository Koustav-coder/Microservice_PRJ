package com.koustav.hotelservice.HotelService.services.impl;

import com.koustav.hotelservice.HotelService.entities.Hotel;
import com.koustav.hotelservice.HotelService.repositories.HotelRepository;
import com.koustav.hotelservice.HotelService.services.HotelService;
import com.koustav.hotelservice.HotelService.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with given id is not found on server !! : "+ id));
    }
}
