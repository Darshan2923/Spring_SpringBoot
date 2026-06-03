package com.rideshare.rideservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequest {

    @NotBlank(message = "Rider ID is required")
    private String riderId;

    @NotNull(message = "PickUp Latitude is required")
    private double pickupLatitude;

    @NotNull(message = "PickUp Longitude is required")
    private double pickupLongitude;

    @NotNull(message = "Dropdown Latitude is required")
    private double dropLatitude;

    @NotNull(message = "Dropdown Longitude is required")
    private double dropLongitude;

    @NotNull(message = "Dropdown Address is required")
    private String dropAddress;

    @NotNull(message = "PickUp Address is required")
    private String pickupAddress;

}
