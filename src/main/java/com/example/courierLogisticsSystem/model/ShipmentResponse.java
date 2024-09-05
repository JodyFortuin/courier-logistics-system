package com.example.courierLogisticsSystem.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ShipmentResponse {
    private UUID referenceId;
    private String driver;
    private String deliveryLocation;
    private LocalDateTime dateLogged;
    private LocalDateTime dateDue;
}
