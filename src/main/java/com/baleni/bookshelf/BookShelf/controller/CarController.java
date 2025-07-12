package com.baleni.bookshelf.BookShelf.controller;

import com.baleni.bookshelf.BookShelf.model.Car;
import com.baleni.bookshelf.BookShelf.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car creatCar(@RequestBody Car car){
        return carService.addCar(car);
    }



}
