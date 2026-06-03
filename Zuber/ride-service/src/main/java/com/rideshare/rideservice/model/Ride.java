package com.rideshare.rideservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        // Who requested the ride
        @Column(nullable = false)
        private String rideId;

        // Who accepted the ride(null until matched)
        @Column(nullable = false)
        private String driverId;

        @Column(nullable = false)
        private double pickupLatitude;

        @Column(nullable = false)
        private double pickupLongitude;

        private String pickupAddress;

        @Column(nullable = false)
        private double dropLatitude;

        @Column(nullable = false)
        private double dropLongitude;

        @Column(nullable = false)
        private String dropAddress;

        // Ride status - tracks the lifecycle
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private RideStatus status;

        // Fare details
        private double estimatedFare;
        private double actualFare;

        // Timestamps
        @CreationTimestamp
        private LocalDateTime createAt;
        @UpdateTimestamp
        private LocalDateTime updatedAt;

        private LocalDateTime startedAt;
        private LocalDateTime completedAt;
}
