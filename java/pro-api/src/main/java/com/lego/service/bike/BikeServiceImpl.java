package com.lego.service.bike;

import com.lego.exception.resourceExceptions.ResourceConflictException;
import com.lego.model.Bike;
import com.lego.repository.jpa.BikeRepository;
import com.lego.repository.jpa.CountryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BikeServiceImpl implements BikeService {
    private BikeRepository bikeRepository;
    private CountryRepository countryRepository;

    public BikeServiceImpl(BikeRepository bikeRepository, CountryRepository countryRepository) {
        this.bikeRepository = bikeRepository;
        this.countryRepository = countryRepository;
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
