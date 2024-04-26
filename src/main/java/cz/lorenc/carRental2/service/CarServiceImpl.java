package cz.lorenc.carRental2.service;

import cz.lorenc.carRental2.dto.CarDtoIn;
import cz.lorenc.carRental2.dto.CarDtoOut;
import cz.lorenc.carRental2.dto.CarDtoWhoRentedTheCar;
import cz.lorenc.carRental2.dto.mapper.CarMapper;
import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import cz.lorenc.carRental2.entity.repositories.CarRepository;
import cz.lorenc.carRental2.entity.repositories.LoanRepository;
import cz.lorenc.carRental2.entity.repositories.PersonRepository;
import cz.lorenc.carRental2.service.util.PrivateMethodsForExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl extends PrivateMethodsForExceptions implements CarService{

    private final CarMapper carMapper;

    public CarServiceImpl(PersonRepository personRepository,
                          LoanRepository loanRepository,
                          CarRepository carRepository,
                          CarMapper carMapper
    ) {
        super(personRepository, loanRepository, carRepository);
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDtoOut> getAll() {
        return carMapper.listToDTOs(carRepository.findAll());
    }

    @Override
    public CarDtoOut getCar(long id) {
        CarEntity fetchedCar = fetchCarById(id, "getCar from CarServiceImpl");
        return carMapper.toDtoOut(fetchedCar);
    }

    @Override
    public CarDtoOut addCar(CarDtoIn carDTOin) {
        CarEntity savedCar = carRepository.save(carMapper.toEntity(carDTOin));
        return carMapper.toDtoOut(savedCar);
    }

    @Override
    public CarDtoOut updateCar(long id, CarDtoIn carDTOin) {
        CarEntity fetchedEntity = fetchCarById(id, "updateCar from CarServiceImpl"); // we need to get the owner attribute
        carMapper.updateEntity(fetchedEntity, carDTOin);
        fetchedEntity.setId(id);
    return carMapper.toDtoOut(carRepository.save(fetchedEntity));
    }

    @Override
    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDtoWhoRentedTheCar> carRenters(long id) {
        List<LoanEntity> listLoans = loanRepository.getAllByCar(fetchCarById(id, "carRenters from CarService"));
        List<PersonEntity> listUsers = listLoans.stream()
                .map(LoanEntity::getUser)
                .collect(Collectors.toList());
        return carMapper.toListRentedCarDto(listUsers, listLoans);
    }

//    // region: Private methods - we are extending the private methods from PrivateMethodsForExceptions...
//
//    private CarEntity fetchCarById(long id) {
//        Optional<CarEntity> fetchedCar = carRepository.findById(id);
//        if (fetchedCar.isPresent())
//            return fetchedCar.get();
//        throw new EntityNotFoundException("Car id: " + id + " wasn't found in the database...");
//    }
//
//    // endregion

}
