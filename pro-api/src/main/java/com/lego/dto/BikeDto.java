package com.lego.dto;

import com.lego.domain.Bike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BikeDto {
    @Size(max = 30)
    @NotBlank
    private String serial;
    @NotNull
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
}
