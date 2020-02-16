package com.lego.proapi.configuration;


import com.lego.proapi.domain.Bike;
import com.lego.proapi.repository.BikeRepository;
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
        loadBikes();
    }

    private void loadBikes() {
        List<Bike> bikes = IntStream.range(1, 20)
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
