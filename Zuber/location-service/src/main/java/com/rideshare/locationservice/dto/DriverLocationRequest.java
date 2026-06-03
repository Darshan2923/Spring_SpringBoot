package com.rideshare.locationservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverLocationRequest {
    private String driverId;
    private double latitude;
    private double longitude;
}
