package com.lcwd.hotel.hotelservice.services;

import java.util.List;

import com.lcwd.hotel.hotelservice.model.Hotel;

public interface HotelService {

    // create
    Hotel create(Hotel hotel);

    // get all hotels
    List<Hotel> getAll();

    // get single
    Hotel getById(String id);
}
