package com.lego.dto;

import com.lego.model.Bike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BikeDto {
    @NotBlank
    private String serial;
    @PastOrPresent
    private LocalDate purchaseDate;
    @PositiveOrZero
    private double weight;
    @PositiveOrZero
    private double price;

    public BikeDto(Bike bike) {
        serial = bike.getSerial();
        purchaseDate = bike.getPurchaseDate();
        weight = bike.getWeight();
        price = bike.getPrice();
    }

    public static Bike toBike(BikeDto bikeDto) {
        return Bike.builder()
                .serial(bikeDto.serial)
                .purchaseDate(bikeDto.purchaseDate)
                .weight(bikeDto.weight)
                .price(bikeDto.price)
                .build();
    }
}
