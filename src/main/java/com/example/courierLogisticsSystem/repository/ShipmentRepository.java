package com.example.courierLogisticsSystem.repository;

import com.example.courierLogisticsSystem.db.DB;
import com.example.courierLogisticsSystem.model.ShipmentStatus;
import com.example.courierLogisticsSystem.model.TrackShipmentResponse;
import com.example.courierLogisticsSystem.model.TrackingDataResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class ShipmentRepository implements ShipmentInterface {

    public void createShipment(final UUID referenceId, final Integer driverId, final Integer locationId, final ShipmentStatus shipmentStatus, final LocalDateTime dateLogged, final LocalDateTime dateDue) {
        try(var conn = DB.connect(false)) {
            PreparedStatement st = conn.prepareStatement("INSERT INTO shipments (reference_id,driver_id,delivery_location_id,status,datelogged,datedue) VALUES (?,?,?,?,?,?);");
            st.setObject(1, referenceId);
            st.setInt(2, driverId);
            st.setInt(3, locationId);
            st.setString(4, shipmentStatus.toString());
            st.setTimestamp(5, Timestamp.valueOf(dateLogged));
            st.setTimestamp(6, Timestamp.valueOf(dateDue));

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
    }

    public void updateShipment(final UUID referenceId, final Integer driverId, final Integer deliveryLocationId){
        try(var conn = DB.connect(false)) {
            if (driverId != null) {
                PreparedStatement st = conn.prepareStatement("UPDATE shipments SET driver_id = ? WHERE reference_id = ?");
                st.setInt(1, driverId);
                st.setObject(2, referenceId);

                st.executeUpdate();
                st.close();
            }

            if (deliveryLocationId != null) {
                PreparedStatement st2 = conn.prepareStatement("UPDATE shipments SET delivery_location_id = ? WHERE reference_id = ?");
                st2.setInt(1, deliveryLocationId);
                st2.setObject(2, referenceId);

                st2.executeUpdate();
                st2.close();
            }
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
    }

    public void createShipmentTracking(final UUID referenceId, final Integer driverId, final Integer capacity) {
        try(var conn = DB.connect(false)) {
            PreparedStatement st = conn.prepareStatement("UPDATE tracking SET reference_id = ?, capacity = ? WHERE driver_id = ?");
            st.setObject(1, referenceId);
            st.setInt(2, capacity);
            st.setInt(3, driverId);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
    }

    public void updateShipmentDriverForTracking(final UUID referenceId, final Integer driverId){
        try(var conn = DB.connect(false)) {
            PreparedStatement st = conn.prepareStatement("UPDATE tracking SET reference_id = ? WHERE reference_id = ?");
            st.setObject(1, null);
            st.setObject(2, referenceId);

            st.executeUpdate();
            st.close();

            PreparedStatement st2 = conn.prepareStatement("UPDATE tracking SET reference_id = ? WHERE driver_id = ?");
            st2.setObject(1, referenceId);
            st2.setInt(2, driverId);

            st2.executeUpdate();
            st2.close();
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
    }

    public void updateShipmentLocationForTracking(final UUID referenceId, final Integer currentLocationId) {
        try(var conn = DB.connect(false)) {
            PreparedStatement st = conn.prepareStatement("SELECT location FROM locations WHERE location_id = ?");
            st.setInt(1, currentLocationId);
            ResultSet rs = st.executeQuery();
            rs.next();

            String friendlyLocation = rs.getString(1);
            st.close();

            PreparedStatement st2 = conn.prepareStatement("UPDATE tracking SET current_location_id = ?, current_location = ? WHERE reference_id = ?");
            st2.setInt(1, currentLocationId);
            st2.setString(2, friendlyLocation);
            st2.setObject(3, referenceId);
            st2.executeUpdate();
            st2.close();
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
    }

    public void updateShipmentStatus(final UUID referenceId, final ShipmentStatus status) {
        try(var conn = DB.connect(false)) {
            PreparedStatement st = conn.prepareStatement("UPDATE shipments SET status = ? WHERE reference_id = ?");
            st.setString(1, status.toString());
            st.setObject(2, referenceId);

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
    }

    public String getFriendlyDeliveryLocation(final Integer currentLocationId) {
        try(var conn = DB.connect(false)) {
            PreparedStatement st = conn.prepareStatement("SELECT location FROM locations WHERE location_id = ?");
            st.setInt(1, currentLocationId);
            ResultSet rs = st.executeQuery();
            rs.next();

            String response = rs.getString(1);
            st.close();

            return response;
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
        return null;
    }

    public TrackShipmentResponse getShipmentTracking(final UUID referenceId) {
        try(var conn = DB.connect(false)) {
            PreparedStatement st = conn.prepareStatement("SELECT driver, current_location FROM tracking WHERE reference_id = ?");
            st.setObject(1, referenceId);
            ResultSet rs = st.executeQuery();
            rs.next();

            TrackShipmentResponse response = new TrackShipmentResponse();
            response.setDriver(rs.getString(1));
            response.setCurrentLocation(rs.getString(2));
            st.close();

            return response;
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
        return null;
    }

public List<TrackingDataResponse> getAllDriverTracking() {
    try(var conn = DB.connect(false)) {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tracking");
        ResultSet rs = st.executeQuery();

        List<TrackingDataResponse> responseList = new ArrayList<>();
        while (rs.next()) {
            TrackingDataResponse response = new TrackingDataResponse();
            response.setReferenceId((UUID) rs.getObject(1));
            response.setDriverId(rs.getInt(2));
            response.setDriver(rs.getString(3));
            response.setCurrentLocationId(rs.getInt(4));
            response.setCurrentLocation(rs.getString(5));
            response.setCapacity(rs.getInt(6));
            responseList.add(response);
        }
        st.close();

        return responseList;
        } catch (SQLException e) {
            System.out.print("exception");
            e.printStackTrace();
        }
        return null;
    }
}
