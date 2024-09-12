package com.example.courierLogisticsSystem.service;

import com.example.courierLogisticsSystem.model.*;
import com.example.courierLogisticsSystem.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public ShipmentResponse createShipment(final ShipmentRequest shipmentRequest) {
        UUID referenceId = UUID.randomUUID();
        LocalDateTime dateLogged = LocalDateTime.now();
        LocalDateTime dateDue = LocalDateTime.now().plusDays(3);
        List<TrackingDataResponse> list = shipmentRepository.getAllDriverTracking();
        System.out.print("print list" + list);
        int distance = list.size() -1;
        int closestId = 0;
        int maxCapacity = 20;
        for (var i =0; i<list.size();i++){
            if (list.get(i).getCurrentLocationId() != 0) {
                if (list.get(i).getCurrentLocationId() - 1 <= distance && list.get(i).getCapacity() < maxCapacity) {
                    closestId = i;
                    distance = list.size() - closestId;
                }
            }
        }

        Integer driverId = list.get(closestId).getDriverId();
        Integer capacity = list.get(closestId).getCapacity() + 1;
        Integer deliveryLocationId = shipmentRequest.getDeliveryLocationId();

        System.out.println("create");
        shipmentRepository.createShipment(referenceId, driverId, deliveryLocationId, ShipmentStatus.PROCESSING, dateLogged, dateDue);
        shipmentRepository.createShipmentTracking(referenceId,driverId,capacity);

        String driver = list.get(closestId).getDriver();
        String location = shipmentRepository.getFriendlyDeliveryLocation(shipmentRequest.getDeliveryLocationId());

        ShipmentResponse shipmentResponse = new ShipmentResponse();
        shipmentResponse.setReferenceId(referenceId);
        shipmentResponse.setDriver(driver);
        shipmentResponse.setDeliveryLocation(location);
        shipmentResponse.setDateLogged(dateLogged);
        shipmentResponse.setDateDue(dateDue);
        return shipmentResponse;
    }

    public void updateShipment(final UUID referenceId, final UpdateShipmentRequest updateShipmentRequest) {
        Integer driverId = updateShipmentRequest.getDriverId();
        Integer deliveryLocationId = updateShipmentRequest.getDeliveryLocationId();

        shipmentRepository.updateShipment(referenceId, driverId, deliveryLocationId);
        if (driverId != null) {
            shipmentRepository.updateShipmentDriverForTracking(referenceId, driverId);
        }
    }

    public void updateShipmentTracking(final UUID referenceId, final UpdateShipmentTrackingRequest updateShipmentTrackingRequest) {
        shipmentRepository.updateShipmentLocationForTracking(referenceId, updateShipmentTrackingRequest.getCurrentLocationId());
    }

    public void updateShipmentStatus(final UUID referenceId, final UpdateShipmentStatusRequest updateShipmentStatusRequest) {
        shipmentRepository.updateShipmentStatus(referenceId, updateShipmentStatusRequest.getStatus());
    }

    public TrackShipmentResponse trackShipment(final UUID referenceId) {
        return shipmentRepository.getShipmentTracking(referenceId);
    }
}
