package com.lego.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.lego.model.Bike;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BikeDto {
	private String serial;
	private LocalDate purchaseDate;
	private double weight;
	private double price;
	public BikeDto(Bike bike) {
		serial = bike.getSerial();
		purchaseDate = bike.getPurchaseDate();
		weight = bike.getWeight();
		price = bike.getPrice();
	}
}
