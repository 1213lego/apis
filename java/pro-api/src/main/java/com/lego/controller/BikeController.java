package com.lego.controller;

import com.lego.dto.BikeDto;
import com.lego.dto.BikeMapper;
import com.lego.model.Bike;
import com.lego.service.bike.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bikes")
public class BikeController {
    private BikeService bikeService;
    private final BikeMapper bikeMapper;

    @Autowired
    public BikeController(BikeService bikeService, BikeMapper bikeMapper) {
        this.bikeService = bikeService;
        this.bikeMapper = bikeMapper;
    }

    @GetMapping("/{serial}")
    public BikeDto getBikeById(@PathVariable String serial) {
        return bikeMapper.toBikeDto(bikeService.findById(serial));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BikeDto save(@Valid @RequestBody BikeDto bikeDto) throws IllegalAccessException {
        Bike saved = bikeService.save(bikeMapper.toBike(bikeDto));
        return bikeMapper.toBikeDto(saved);
    }

    @GetMapping
    public Page<BikeDto> findAllBikesAsDto(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "20") int size) {
        return bikeService.findAll(page, size).map(bikeMapper::toBikeDto);
    }
}
