package com.realtime_delivery_tracking.realtime_delivery_tracking.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @KafkaListener(topics = "delivery-location", groupId = "user-group")
    public void deliveryLocation(String location) {
        System.out.println(location);
    }
}
