package com.example.courierLogisticsSystem.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateShipmentRequest {
    private Integer driverId;
    private Integer deliveryLocationId;
}
