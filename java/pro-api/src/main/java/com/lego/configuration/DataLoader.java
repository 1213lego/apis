package com.lego.configuration;

import com.lego.model.Bike;
import com.lego.repository.jpa.BikeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DataLoader {
    private final BikeRepository bikeRepository;

    public DataLoader(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
        loadStates();
    }

    private void loadStates() {
        List<Bike> bikes = IntStream.range(1, 129)
                .mapToObj((i) -> Bike.builder()
                        .price(i * 6.831698)
                        .serial("B" + i)
                        .weight(1.36598 * i * i)
                        .purchaseDate(LocalDate.now())
                        .build())
                .collect(Collectors.toList());
        bikeRepository.saveAll(bikes);
    }
}
