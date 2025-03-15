package com.koustav.hotelservice.HotelService.repositories;

import com.koustav.hotelservice.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
