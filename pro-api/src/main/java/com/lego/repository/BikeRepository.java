package com.lego.repository;

import com.lego.dto.BikeDto;
import com.lego.model.Bike;
import com.lego.util.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BikeRepository extends JpaRepository<Bike,String> {
    boolean existsBikeBySerial(String serial);
    List<Bike> findAllOrderedDescendingByPrice();
    List<Pair<String,Bike>> pair();
    @Query("SELECT new com.lego.dto.BikeDto(b) FROM Bike b")
    List<BikeDto> findAllInDto();
}
