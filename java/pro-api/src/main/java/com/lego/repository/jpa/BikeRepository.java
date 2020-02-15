package com.lego.repository.jpa;

import com.lego.dto.BikeDto;
import com.lego.domain.Bike;
import com.lego.util.Pair;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BikeRepository extends JpaRepository<Bike, String> {
    boolean existsBikeBySerial(String serial);

    List<Bike> findAllOrderedDescendingByPrice();

    List<Pair<String, Bike>> pair();

    @Query("SELECT new com.lego.dto.BikeDto(b) FROM Bike b")
    List<BikeDto> findAllAsDto();

    @Async
    CompletableFuture<Bike> findBySerial(String serial);

    Slice<Bike> queryAllByCreationDateAfter(LocalDateTime creationDate);

    @Query("SELECT new com.lego.dto.BikeDto(b) FROM Bike b")
    Slice<BikeDto> findSliceOfBikesAsDtos(Pageable pageable);

    @Modifying
    @Query("update Bike u set u.weight = ?1 where u.serial = ?2")
    int seWeight(Double weight, String serial);


    void deleteBySerial(String serial);

    @Modifying
    @Query("delete from Bike b where b.serial = ?1")
    int deleteBike(String serial);
}
