package com.rideshare.locationservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.rideshare.locationservice.dto.DriverLocationRequest;
import com.rideshare.locationservice.dto.NearByDriverResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationService {

    private final RedisTemplate<String, String> redisTemplate;

    // Redis key for all drivers location
    private static final String DRIVERS_GEO_KEY = "drivers:locations";

    /**
     * Find nearby drivers within the given radius
     * Called by Matching Service on ride request
     * Maps to Redis GEORADIUS command
     */
    public List<NearByDriverResponse> findNearByDrivers(double latitude, double longitude, double radius) {
        log.info("Finding drivers near lat: {}, long: {} within {}Kms", latitude, longitude, radius);

        Circle searchArea = new Circle(
                new Point(longitude, latitude),
                new Distance(radius, Metrics.KILOMETERS));

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius(DRIVERS_GEO_KEY,
                searchArea,
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeCoordinates().includeDistance()
                        .sortAscending().limit(10));
        List<NearByDriverResponse> nearByDrivers = new ArrayList<>();

        if (results != null) {
            results.getContent().forEach(result -> {
                RedisGeoCommands.GeoLocation<String> location = result.getContent();
                nearByDrivers.add(new NearByDriverResponse(
                        location.getName(),
                        location.getPoint().getY(),
                        location.getPoint().getX(),
                        result.getDistance().getValue()));
            });
        }
        log.info("Found {} drivers nearby", nearByDrivers.size());
        return nearByDrivers;
    }

    /**
     * Remove driver when he goes offline
     * Maps to Redis ZREM command
     */
    public void removeDriver(String driverId) {
        log.info("Removing driver: {}", driverId);
        redisTemplate.opsForGeo().remove(DRIVERS_GEO_KEY, driverId);
    }

    // Update drivers location in Redis.Called every 3 seconds by driver's phone.
    // Maps to Redis GEOADD Command
    public void updateDriverLocation(DriverLocationRequest driverLocationRequest) {
        log.info("Updating location for driver:{}", driverLocationRequest.getDriverId());

        // IMPORTANT: longitude first and then latitude - GEO SPATIAL standard
        Point driverPoint = new Point(
                driverLocationRequest.getLongitude(),
                driverLocationRequest.getLatitude());

        redisTemplate.opsForGeo().add(
                DRIVERS_GEO_KEY,
                driverPoint,
                driverLocationRequest.getDriverId());

        log.info("Location updated for the driver: {}", driverLocationRequest.getDriverId());

    }

}
