package com.rideshare.locationservice.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.rideshare.locationservice.dto.DriverLocationRequest;
import com.rideshare.locationservice.dto.NearByDriverResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationService {

    // Redis key for all drivers location
    private static final String DRIVERS_GEO_KEY = "drivers:locations";

    public List<NearByDriverResponse> findNearByDrivers(double latitude, double longitude, double radius) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findNearByDrivers'");
    }

    public void removeDriver(String driverId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeDriver'");
    }

    // Update drivers location in Redis.Called every 3 seconds by driver's phone.
    // Maps to Redis GEOADD Command
    public void updateDriverLocation(DriverLocationRequest driverLocationRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDriverLocation'");
    }

}
