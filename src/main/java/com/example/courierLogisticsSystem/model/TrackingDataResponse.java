package com.example.courierLogisticsSystem.model;

import lombok.Data;

import java.util.UUID;

@Data
public class TrackingDataResponse {
    private UUID referenceId;
    private Integer driverId;
    private String driver;
    private Integer currentLocationId;
    private String currentLocation;
    private Integer capacity;
}
