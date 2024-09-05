package com.example.courierLogisticsSystem.model;

import lombok.Data;

@Data
public class UpdateShipmentStatusRequest {
    private ShipmentStatus status;
}
