package com.realtime_delivery_tracking.realtime_delivery_tracking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_delivery_tracking.realtime_delivery_tracking.service.DeliveryLocationService;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryLocationController {

    @Autowired
    private DeliveryLocationService deliveryLocationService;

    @GetMapping
    public ResponseEntity<Map<String, String>> getCurrentLocation() {
        // Simulating a location between New York and New Jersey
        double nycLat = 40.7128;
        double nycLon = -74.0060;
        double njLat = 40.7357;
        double njLon = -74.1724;

        double randomLat = nycLat + Math.random() * (njLat - nycLat);
        double randomLon = nycLon + Math.random() * (njLon - nycLon);

        String location = randomLat + " , " + randomLon;

        return new ResponseEntity<>(Map.of("location", location), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateLocation() throws InterruptedException {
        double nycLat = 40.7128;
        double nycLon = -74.0060;
        double njLat = 40.7357;
        double njLon = -74.1724;

        int range = 100;
        while (range > 0) {
            // Generate random location between NYC and New Jersey
            double randomLat = nycLat + Math.random() * (njLat - nycLat);
            double randomLon = nycLon + Math.random() * (njLon - nycLon);

            String location = randomLat + " , " + randomLon;
            deliveryLocationService.updateLocation(location);

            Thread.sleep(1000); // Sleep for 1 second
            range--;
        }
        return new ResponseEntity<>(Map.of("message", "Location Updated"), HttpStatus.OK);
    }

}
