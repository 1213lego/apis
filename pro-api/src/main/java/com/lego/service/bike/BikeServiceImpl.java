package com.lego.service.bike;

import com.lego.domain.Bike;
import com.lego.exception.resourceExceptions.ResourceConflictException;
import com.lego.repository.jpa.BikeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BikeServiceImpl implements BikeService {
    private BikeRepository bikeRepository;

    public BikeServiceImpl(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public JpaRepository<Bike, String> getRepository() {
        return bikeRepository;
    }

    public Bike save(Bike bike) throws ResourceConflictException, IllegalAccessException {
        bike.setNew(true);
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


    //Paging and sorting
    private void examplesPaginAndSorting() {
        Sort.TypedSort<Bike> bike = Sort.sort(Bike.class);
        Sort sort = bike.by(Bike::getSerial).ascending()
                .and(bike.by(Bike::getWeight));
    }

}
