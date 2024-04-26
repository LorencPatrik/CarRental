package cz.lorenc.carRental2.controller;

import cz.lorenc.carRental2.dto.PersonDtoIn;
import cz.lorenc.carRental2.dto.PersonDtoOut;
import cz.lorenc.carRental2.dto.PersonDtoBorrowedCar;
import cz.lorenc.carRental2.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people/")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getAll")
    public List<PersonDtoOut> getAll(){
        return personService.getAllpersons();
    }

    @GetMapping("/getPerson/{id}")
    public PersonDtoOut getPersonById(@PathVariable("id") Long id) {
        return personService.getPerson(id);
    }

    @PostMapping("/addPerson")
    public PersonDtoOut addPerson(@RequestBody PersonDtoIn personDTOin) {
        return personService.addPerson(personDTOin);
    }

    @PutMapping("/updatePerson/{id}")
    public PersonDtoOut personUpdate(@PathVariable("id") long id, @RequestBody PersonDtoIn personDTOin) {
        return personService.updatePerson(id, personDTOin);
    }

    @DeleteMapping("/deletePerson/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }

    @GetMapping("/showBorrowedCars/{id}")
    public List<PersonDtoBorrowedCar> borrowedCars(@PathVariable("id") long id) {
        return personService.borrowedCars(id);
    }

}
