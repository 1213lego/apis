package com.lego.service.bike;

import com.lego.exception.resourceExceptions.ResourceConflictException;
import com.lego.model.Bike;
import com.lego.model.State;
import com.lego.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BikeServiceImpl implements BikeService {
    private BikeRepository bikeRepository;

    @Autowired
    public BikeServiceImpl(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
        System.out.println(bikeRepository.findAllOrderedDescendingByPrice());
        System.out.println(bikeRepository.existsBikeBySerial("aaa"));
        System.out.println(bikeRepository.pair());
        System.out.println(bikeRepository.findAllAsDto());
    }

    @Override
    public JpaRepository<Bike, String> getRepository() {
        return bikeRepository;
    }

    public Bike save(Bike bike) throws ResourceConflictException, IllegalAccessException {
        bike.setNew(true);
        bike.setState(State.ACTIVE_STATE);
        bike = BikeService.super.save(bike);
        return bike;
    }

    @Override
    public Class<Bike> getEntityType() {
        return Bike.class;
    }

    @Override
    public void delete(Bike entity) {

    }
}
