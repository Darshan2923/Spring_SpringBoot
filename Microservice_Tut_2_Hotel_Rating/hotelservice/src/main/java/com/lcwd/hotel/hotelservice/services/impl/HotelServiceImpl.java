package com.lcwd.hotel.hotelservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.hotelservice.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.hotelservice.model.Hotel;
import com.lcwd.hotel.hotelservice.repo.HotelRepo;
import com.lcwd.hotel.hotelservice.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel create(Hotel hotel) {
        hotel.setHotelId(UUID.randomUUID().toString());
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getById(String id) {
        return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found"));
    }

}
