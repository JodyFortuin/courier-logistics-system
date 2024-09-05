package com.example.courierLogisticsSystem;

import com.example.courierLogisticsSystem.model.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestUtils {

    public static UUID getUUID() {
        return UUID.fromString("073578de-63c8-4250-94ea-8da0d28d08a4");
    }


    public static ShipmentRequest getShipmentRequest() {
        ShipmentRequest response = new ShipmentRequest();
        response.setDeliveryLocationId(5);
        return response;
    }

    public static ShipmentResponse getShipmentResponse() {
        ShipmentResponse response = new ShipmentResponse();
        response.setReferenceId(getUUID());
        response.setDriver("John");
        response.setDeliveryLocation("Atlantic Seaboard");
        response.setDateLogged(LocalDateTime.now());
        response.setDateDue(LocalDateTime.now().plusDays(3));
        return response;
    }

    public static UpdateShipmentTrackingRequest getUpdateShipmentTrackingRequest() {
        UpdateShipmentTrackingRequest response = new UpdateShipmentTrackingRequest();
        response.setCurrentLocationId(3);
        return response;
    }

    public static TrackShipmentResponse getTrackShipmentResponse() {
        TrackShipmentResponse response = new TrackShipmentResponse();
        response.setDriver("John");
        response.setCurrentLocation("Atlantic Seaboard");
        return response;
    }
}
