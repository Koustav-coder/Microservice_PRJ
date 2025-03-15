package com.koustav.hotelservice.HotelService.controllers;

import com.koustav.hotelservice.HotelService.entities.Hotel;
import com.koustav.hotelservice.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    //single user get
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> get(@PathVariable String hotelId){
        Hotel hotel = hotelService.get(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }

    //all user get
    @GetMapping("/allHotels")
    public ResponseEntity<List<Hotel>> getAll (){
        List<Hotel> allHotels = hotelService.getAll();
        return ResponseEntity.ok(allHotels);
    }
}
