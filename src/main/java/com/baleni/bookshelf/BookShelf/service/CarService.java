package com.baleni.bookshelf.BookShelf.service;

import com.baleni.bookshelf.BookShelf.model.Car;
import com.baleni.bookshelf.BookShelf.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car addCar(Car car){
        car.setCarId(UUID.randomUUID().toString().split("-")[0]);
        return carRepository.save(car);
    }

    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public Car getCarByCarId(String carId){
        return carRepository.findByCarId(carId);
    }

    public List<Car> getCarByType(String type){
        return carRepository.findByType(type);
    }

    public List<Car> getCarByYear(int year){
        return carRepository.findByYear(year);
    }

    public List<Car> getCarByBrand(String brand){
        return carRepository.findByBrand(brand);
    }

}
