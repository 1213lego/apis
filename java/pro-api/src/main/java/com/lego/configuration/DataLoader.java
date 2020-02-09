package com.lego.configuration;

import com.lego.model.Bike;
import com.lego.model.State;
import com.lego.repository.BikeRepository;
import com.lego.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {
    private final BikeRepository bikeRepository;
    private StateRepository stateRepository;

    @Autowired
    public DataLoader(StateRepository stateRepository, BikeRepository bikeRepository) {
        this.stateRepository = stateRepository;
        this.bikeRepository = bikeRepository;
        loadStates();
    }

    private void loadStates() {
        List<State> states = Arrays.asList(State.ACTIVE_STATE, State.INACTIVE_STATE);
        stateRepository.saveAll(states);
        List<Bike> bikes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bikes.add(Bike.builder()
                    .price(i*5.698)
                    .serial("S"+i)
                    .weight(1.36598*i)
                    .state(State.ACTIVE_STATE)
                    .purchaseDate(LocalDate.now())
                    .build());
        }
        bikeRepository.saveAll(bikes);
    }
}
