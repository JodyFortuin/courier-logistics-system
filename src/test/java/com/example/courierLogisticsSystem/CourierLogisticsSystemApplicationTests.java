package com.example.courierLogisticsSystem;

import com.example.courierLogisticsSystem.model.*;
import com.example.courierLogisticsSystem.service.ShipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourierLogisticsSystemApplicationTests {

	@MockBean
	ShipmentService shipmentService;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setup(){
		ShipmentRequest shipmentRequest = TestUtils.getShipmentRequest();
		ShipmentResponse shipmentResponse = TestUtils.getShipmentResponse();
		TrackShipmentResponse trackShipmentResponse = TestUtils.getTrackShipmentResponse();
		UUID uuid = TestUtils.getUUID();

		when(shipmentService.createShipment(shipmentRequest)).thenReturn(shipmentResponse);
		when(shipmentService.trackShipment(uuid)).thenReturn(trackShipmentResponse);
	}

	@Test
	public void test_createShipment() {
		ShipmentRequest shipmentRequest = TestUtils.getShipmentRequest();

		ShipmentResponse response = shipmentService.createShipment(shipmentRequest);

		assertThat(response).isInstanceOf(ShipmentResponse.class);
		assertThat(response.getReferenceId()).isEqualTo(UUID.fromString("073578de-63c8-4250-94ea-8da0d28d08a4"));
		assertThat(response.getDriver()).isEqualTo("John");
		assertThat(response.getDeliveryLocation()).isEqualTo("Atlantic Seaboard");
		assertThat(response.getDateDue().toLocalDate()).isEqualTo(response.getDateLogged().toLocalDate().plusDays(3));
	}

	@Test
	public void test_trackShipment(){
		ShipmentRequest shipmentRequest = TestUtils.getShipmentRequest();

		shipmentService.createShipment(shipmentRequest);
		TrackShipmentResponse track = shipmentService.trackShipment(TestUtils.getUUID());
		assertThat(track).isInstanceOf(TrackShipmentResponse.class);
		assertThat(track.getDriver()).isEqualTo("John");
		assertThat(track.getCurrentLocation()).isEqualTo("Atlantic Seaboard");
	}
}
