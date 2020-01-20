package com.lego.service.bike;

import com.lego.exception.resourceExceptions.ResourceAlreadyExistsException;
import com.lego.model.Bike;
import com.lego.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Bike save(Bike bike) throws ResourceAlreadyExistsException {
        /*Optional<Bike> optionalBike = BikeService.super.findById(bike.getSerial());
        optionalBike.ifPresent((b) -> {
            throw new ResourceAlreadyExistsException(b.getSerial(), b.getClass().getSimpleName());
        });*/
        try {
            bike.setNew(true);
            bike = BikeService.super.save(bike);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(e.getCause(), bike.getSerial(), bike.getClass());
        }
        return bike;
    }
}
