package com.example.courierLogisticsSystem.repository;

import com.example.courierLogisticsSystem.model.ShipmentStatus;
import com.example.courierLogisticsSystem.model.TrackShipmentResponse;
import com.example.courierLogisticsSystem.model.TrackingDataResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ShipmentInterface {
    public void createShipment(final UUID referenceId, final Integer driverId, final Integer locationId, final ShipmentStatus shipmentStatus, final LocalDateTime dateLogged, final LocalDateTime dateDue);

    public void updateShipment(final UUID referenceId, final Integer driverId, final Integer deliveryLocationId);

    public void createShipmentTracking(final UUID referenceId, final Integer driverId, final Integer capacity);

    public void updateShipmentDriverForTracking(final UUID referenceId, final Integer driverId);

    public void updateShipmentLocationForTracking(final UUID referenceId, final Integer currentLocationId);

    public void updateShipmentStatus(final UUID referenceId, final ShipmentStatus status);

    public String getFriendlyDeliveryLocation(final Integer currentLocationId);

    public TrackShipmentResponse getShipmentTracking(final UUID referenceId);

    public List<TrackingDataResponse> getAllDriverTracking();
    }
