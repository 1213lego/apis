package com.lego.controller;

import com.lego.dto.BikeDto;
import com.lego.model.Bike;
import com.lego.service.bike.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;


@RestController
@RequestMapping("/bikes")
public class BikeController {
    private BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;

    }

    @PostMapping
    public BikeDto save(@Valid @RequestBody BikeDto bike){
        return new BikeDto(bikeService.save(BikeDto.toBike(bike)));
    }

    @GetMapping("/dtos")
    public Page<BikeDto> findAllBikesAsDto(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "20") int size) {
        return bikeService.findAll(page, size).
                map((BikeDto::new));
    }

    @GetMapping
    public Page<Bike> findAllBikes(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "20") int size) {
        return bikeService.findAll(page, size);
    }

    @GetMapping("/some")
    public String some() {
        return "Helllo edited opppppppppppppssss";
    }
}
