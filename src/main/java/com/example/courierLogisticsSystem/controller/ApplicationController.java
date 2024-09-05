package com.example.courierLogisticsSystem.controller;

import com.example.courierLogisticsSystem.model.*;
import com.example.courierLogisticsSystem.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Controller
@RequestMapping("/api/v1")
public class ApplicationController {
    private final ShipmentService shipmentService;

    @PostMapping("/createShipment")
    public ResponseEntity<ShipmentResponse> createShipment(
            @RequestBody @Valid ShipmentRequest shipmentRequest) {
        ShipmentResponse response =  shipmentService.createShipment(shipmentRequest);
        if (response != null){
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @PutMapping("/updateShipment/{referenceId}")
    public void updateShipment(
            @PathVariable UUID referenceId,
            @RequestBody UpdateShipmentRequest updateShipmentRequest) {
        shipmentService.updateShipment(referenceId, updateShipmentRequest);
    }

    @PutMapping("/updateShipmentTracking/{referenceId}")
    public void updateShipmentTracking(
            @PathVariable UUID referenceId,
            @RequestBody UpdateShipmentTrackingRequest updateShipmentTrackingRequest) {
        shipmentService.updateShipmentTracking(referenceId, updateShipmentTrackingRequest);
    }

    @PutMapping("/updateShipmentStatus/{referenceId}")
    public void updateShipmentStatus(
            @PathVariable UUID referenceId,
            @RequestBody UpdateShipmentStatusRequest updateShipmentStatusRequest) {
        shipmentService.updateShipmentStatus(referenceId, updateShipmentStatusRequest);
    }

    @GetMapping("/trackShipment/{referenceId}")
    public ResponseEntity<TrackShipmentResponse> trackShipment(
            @PathVariable UUID referenceId) {
        TrackShipmentResponse response = shipmentService.trackShipment(referenceId);
        if (response != null){
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }
}
