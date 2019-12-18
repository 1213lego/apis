package com.lego.service.bike;

import com.lego.dto.BikeDto;
import com.lego.model.Bike;
import com.lego.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BikeServiceImpl implements BikeService {
    private BikeRepository bikeRepository;
    @Autowired
    public BikeServiceImpl(BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
        System.out.println(bikeRepository.findAllOrderedDescendingByPrice());
        System.out.println(bikeRepository.existsBikeBySerial("aaa"));
        System.out.println(bikeRepository.pair());
        System.out.println(bikeRepository.findAllInDto());
    }
    @Override
    public JpaRepository<Bike, String> getRepository() {
        return bikeRepository;
    }
}
