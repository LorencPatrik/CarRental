package cz.lorenc.carRental2.dto.mapper;

import cz.lorenc.carRental2.dto.CarDtoIn;
import cz.lorenc.carRental2.dto.CarDtoOut;
import cz.lorenc.carRental2.dto.CarDtoWhoRentedTheCar;
import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "carLoans", expression = "java(removeCarFromLoans(source.getCarLoans()))") // for every carLoans will be called method removeCarFromLoans with parameter
//  @Mapping(target = "carLoans", source = "source.carLoans", qualifiedByName = "removeCarFromLoans") // for every carLoans will be called method removeCarFromLoans. It's a parameter is in source...
    CarDtoOut toDtoOut(CarEntity source);


    default List<LoanEntity> removeCarFromLoans(List<LoanEntity> source) { // this is my implementation which will be called from generated sources
        if (source == null) // when we crate a new car (the car doesn't have any loan...)
            return null;
        for (LoanEntity oneEntity : source) {
            oneEntity.setCar(null); // we don't want to have the same car in the list for the second time
            oneEntity.getUser().setUserLoans(null); // we want to have in the list only user without his userLoans -> infinite recursion = stack overflow...
        }
        return source;
    } //   @Named("removeCarFromLoans") // this is an annotation for this method if we use qualifiedByName instead of espression...

    default List<CarDtoOut> listToDTOs(List<CarEntity> source) { // default listToDTOs won't be in the generated sources. This is my implementation.
        List<CarDtoOut> listDTOs =  new ArrayList<>();
        for (CarEntity oneCar : source) {
            oneCar.setCarLoans(removeCarFromLoans(oneCar.getCarLoans()));
            listDTOs.add(toDtoOut(oneCar)); // we call prepared function for mapping CarEntity to CarDTOout with carLoans ignore (carLoans.car)
        }
        return listDTOs;
    }

    List<LoanEntity> toListDtoOut(List<LoanEntity> source); // we need this method for forcing generated sources use our toEntity with ignore attributes
    // because those attributes are in the list<LoanEntity> userLoans

   CarEntity toEntity(CarDtoIn source);

    void updateEntity(@MappingTarget CarEntity target, CarDtoIn source);

    default List<CarDtoWhoRentedTheCar> toListRentedCarDto(List<PersonEntity> personSource, List<LoanEntity> loanSource) {
        List<CarDtoWhoRentedTheCar> newList = new ArrayList<>();
        Iterator<PersonEntity> personIterator = personSource.iterator();
        Iterator<LoanEntity> loanIterator = loanSource.iterator();
        while (personIterator.hasNext() && loanIterator.hasNext()) {
            CarDtoWhoRentedTheCar newDto = toRentedCarDto(personIterator.next(), loanIterator.next());
            newList.add(newDto);
        }
        return newList;
    }

    @Mapping(target = "name", source = "personSource.name") // it will be working without all of these mappings...
    @Mapping(target = "address", source = "personSource.address")
    @Mapping(target = "city", source = "personSource.city")
    @Mapping(target = "rentalDate", source = "loanSource.rentalDate")
    @Mapping(target = "returnDate", source = "loanSource.returnDate")
    @Mapping(target = "kilometersDriven", source = "loanSource.kilometersDriven")
    CarDtoWhoRentedTheCar toRentedCarDto(PersonEntity personSource, LoanEntity loanSource);

}

//    a different (easier) way how to ignore car attribute in the LoanEntity for mapping:
//
//    List<LoanEntity> toListEntities(List<LoanEntity> source); // toDtoOut calls this method (without carLoans ignore = true)
//
//    @Mapping(target = "car", ignore = true) // toListEntities calls this method for every LoanEntity and will be applied car ignore...
//    LoanEntity toEntity (LoanEntity source);