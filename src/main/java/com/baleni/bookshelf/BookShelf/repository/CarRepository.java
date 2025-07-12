package com.baleni.bookshelf.BookShelf.repository;

import com.baleni.bookshelf.BookShelf.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    Car findByCarId(String carId);

    List<Car> findByType(String type);

    List<Car> findByYear(int year);

    List<Car> findByBrand(String brand);
}
