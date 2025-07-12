package com.baleni.bookshelf.BookShelf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    private String carId;
    private String brand;
    private String model;
    private int year;
    private CarType type;


}
