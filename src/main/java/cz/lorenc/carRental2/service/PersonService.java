package cz.lorenc.carRental2.service;

import cz.lorenc.carRental2.dto.PersonDtoIn;
import cz.lorenc.carRental2.dto.PersonDtoOut;
import cz.lorenc.carRental2.dto.PersonDtoBorrowedCar;

import java.util.List;

public interface PersonService {

    List<PersonDtoOut> getAllpersons();

    PersonDtoOut getPerson(long id);

    PersonDtoOut addPerson(PersonDtoIn personDTOin);

    PersonDtoOut updatePerson(long id, PersonDtoIn personDTOin);

    void deletePerson(long id);

    List<PersonDtoBorrowedCar> borrowedCars(long id);

}
