package com.example.courierLogisticsSystem;

import com.example.courierLogisticsSystem.db.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class CourierLogisticsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourierLogisticsSystemApplication.class, args);

		try (var connection =  DB.connect(true)){
			System.out.println("Connected to the PostgreSQL database.");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}
