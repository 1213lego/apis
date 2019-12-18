package com.lego.controller;

import com.lego.model.Bike;
import com.lego.service.bike.BikeService;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bikes")
public class BikeController {
    private BikeService bikeService;
    @Autowired
    public BikeController(BikeService bikeService){
        this.bikeService = bikeService;

    }
    @PostMapping
    public Bike save(@RequestBody Bike bike){
        return bikeService.save(bike);
    }
    @GetMapping
    public Page<Bike> findAll(@RequestParam(name = "page",defaultValue = "0") int page,
    		@RequestParam(name = "size", defaultValue = "20") int size){
        return bikeService.findAll(page, size);
    }
    @GetMapping("/some")
    public String some(){
        return "Helllo edited opppppppppppppssss";
    }
}
