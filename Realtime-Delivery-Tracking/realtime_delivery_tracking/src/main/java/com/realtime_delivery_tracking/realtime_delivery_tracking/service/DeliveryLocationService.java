package com.realtime_delivery_tracking.realtime_delivery_tracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.realtime_delivery_tracking.realtime_delivery_tracking.constants.AppConstant;

@Service
public class DeliveryLocationService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public boolean updateLocation(String location) {
        kafkaTemplate.send(AppConstant.DELIVERY_LOCATION, location);
        return true;
    }
}
