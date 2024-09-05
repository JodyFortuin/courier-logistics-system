package com.example.courierLogisticsSystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ShipmentRequest {
    @NotNull
    private Integer deliveryLocationId;
}
