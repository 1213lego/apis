package com.lego.proapi.controller;


import com.lego.proapi.domain.Bike;
import com.lego.proapi.dto.BikeDto;
import com.lego.proapi.dto.BikeMapper;
import com.lego.proapi.repository.BikeRepository;
import com.lego.proapi.service.bike.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bikes")
public class BikeController {
    private final BikeService bikeService;
    private final BikeMapper bikeMapper;
    private final BikeRepository bikeRepository;

    @Autowired
    public BikeController(BikeService bikeService, BikeMapper bikeMapper, BikeRepository bikeRepository) {
        this.bikeService = bikeService;
        this.bikeMapper = bikeMapper;
        this.bikeRepository = bikeRepository;
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

    @GetMapping("/slice")
    public Slice<BikeDto> testSlice(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "21") int size) {
        return bikeRepository
                .findSliceOfBikesAsDtos(PageRequest.of(page, size));
    }

    @GetMapping("/test")
    public void tes(@RequestHeader(value = "data") String data) {
        System.out.println(data);
    }
}
