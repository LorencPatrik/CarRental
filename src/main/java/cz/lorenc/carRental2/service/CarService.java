package cz.lorenc.carRental2.service;

import cz.lorenc.carRental2.dto.CarDtoIn;
import cz.lorenc.carRental2.dto.CarDtoOut;
import cz.lorenc.carRental2.dto.CarDtoWhoRentedTheCar;

import java.util.List;

public interface CarService {

    List<CarDtoOut> getAll();

    CarDtoOut getCar(long id);

    CarDtoOut addCar(CarDtoIn carDTOin);

    CarDtoOut updateCar(long id, CarDtoIn carDTOin);

    void deleteCar(long id);

    List<CarDtoWhoRentedTheCar> carRenters(long id);

}
