package cz.lorenc.carRental2.controller;

import cz.lorenc.carRental2.dto.CarDtoIn;
import cz.lorenc.carRental2.dto.CarDtoOut;
import cz.lorenc.carRental2.dto.CarDtoWhoRentedTheCar;
import cz.lorenc.carRental2.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getAll")
    List<CarDtoOut> getAll() {
        return carService.getAll();
    }

    @GetMapping("/getCar/{id}")
    CarDtoOut getOneCar(@PathVariable("id") Long id) {
        return carService.getCar(id);
    }

    @PostMapping("/addCar")
    CarDtoOut addCar(@RequestBody CarDtoIn carDTOin) {
        return carService.addCar(carDTOin);
    }

    @PutMapping("/updateCar/{id}")
    CarDtoOut updateCar(@PathVariable("id") long id, @RequestBody CarDtoIn carDTOin) {
        return carService.updateCar(id, carDTOin);
    }

    @DeleteMapping("/deleteCar/{id}")
    void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/showCarRenters/{id}")
    List<CarDtoWhoRentedTheCar> showCarRenters(@PathVariable("id") long id) {
        return carService.carRenters(id);
    }

 }
