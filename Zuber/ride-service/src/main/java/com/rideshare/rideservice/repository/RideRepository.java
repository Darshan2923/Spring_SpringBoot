package com.rideshare.rideservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideshare.rideservice.model.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {

}
