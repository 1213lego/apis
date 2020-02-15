package com.lego.dto;

import com.lego.domain.Bike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BikeMapper {
    BikeDto toBikeDto(Bike bike);

    Bike toBike(BikeDto bikeDto);

    List<BikeDto> toBikeDtos(List<Bike> bikes);
}
