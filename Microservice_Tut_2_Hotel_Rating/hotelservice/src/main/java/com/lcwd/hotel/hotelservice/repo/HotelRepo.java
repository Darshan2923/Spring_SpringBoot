package com.lcwd.hotel.hotelservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.hotel.hotelservice.model.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
