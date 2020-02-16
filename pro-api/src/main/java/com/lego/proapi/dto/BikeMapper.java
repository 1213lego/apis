package com.lego.proapi.dto;


import com.lego.proapi.domain.Bike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BikeMapper {
    BikeDto toBikeDto(Bike bike);

    Bike toBike(BikeDto bikeDto);

    List<BikeDto> toBikeDtos(List<Bike> bikes);
}
