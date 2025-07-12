package com.baleni.bookshelf.BookShelf.controller;

import com.baleni.bookshelf.BookShelf.model.Car;
import com.baleni.bookshelf.BookShelf.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars") // Base path for all car-related endpoints
public class CarController {

    @Autowired
    private CarService carService;

    // Endpoint to create a new car
    // Maps to POST /cars
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 Created on successful creation
    public Car createCar(@RequestBody Car car){
        // The service layer handles adding the car, including ID generation
        return carService.addCar(car);
    }

    // Endpoint to retrieve all cars
    // Maps to GET /cars
    @GetMapping
    public List<Car> getAllCars(){
        return carService.findAllCars();
    }

    // Endpoint to retrieve a car by its unique carId
    // Maps to GET /cars/{carId}
    @GetMapping("/{carId}")
    public Car getCarById(@PathVariable String carId){
        return carService.getCarByCarId(carId);
    }

    // Endpoint to retrieve cars by their type
    // Maps to GET /cars/type/{type}
    @GetMapping("/type/{type}")
    public List<Car> getCarsByType(@PathVariable String type){
        return carService.getCarByType(type);
    }

    // Endpoint to retrieve cars by their year
    // Maps to GET /cars/year/{year}
    @GetMapping("/year/{year}")
    public List<Car> getCarsByYear(@PathVariable int year){
        return carService.getCarByYear(year);
    }

    // Endpoint to retrieve cars by their brand
    // Maps to GET /cars/brand/{brand}
    @GetMapping("/brand/{brand}")
    public List<Car> getCarsByBrand(@PathVariable String brand){
        return carService.getCarByBrand(brand);
    }

    // You might also want to add PUT (update) and DELETE endpoints:


    // Example: Endpoint to update an existing car
    // Maps to PUT /cars/{carId}
    @PutMapping("/{carId}")
    public Car updateCar(@PathVariable String carId, @RequestBody Car car) {
        // You would typically fetch the existing car, update its fields, and then save it
        // For simplicity, this example assumes carId is passed in the request body or handled in service
        car.setCarId(carId); // Ensure the ID from path is set
        return carService.addCar(car); // 'save' method in repository handles update if ID exists
    }

    // Example: Endpoint to delete a car by its unique carId
    // Maps to DELETE /cars/{carId}
    @DeleteMapping("/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Returns HTTP 204 No Content on successful deletion
    public void deleteCar(@PathVariable String carId) {
        carService.deleteCar(carId); // Assuming you add a delete method in CarService
    }

}
