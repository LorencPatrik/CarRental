package cz.lorenc.carRental2.service;

import cz.lorenc.carRental2.dto.PersonDtoIn;
import cz.lorenc.carRental2.dto.PersonDtoOut;
import cz.lorenc.carRental2.dto.PersonDtoBorrowedCar;
import cz.lorenc.carRental2.dto.mapper.PersonMapper;
import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import cz.lorenc.carRental2.entity.repositories.CarRepository;
import cz.lorenc.carRental2.entity.repositories.LoanRepository;
import cz.lorenc.carRental2.entity.repositories.PersonRepository;
import cz.lorenc.carRental2.service.util.PrivateMethodsForExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl extends PrivateMethodsForExceptions implements PersonService {

    private PersonMapper personMapper;
    public PersonServiceImpl(PersonRepository personRepository,
                             LoanRepository loanRepository,
                             CarRepository carRepository,
                             PersonMapper personMapper
    ) {
        super(personRepository, loanRepository, carRepository);
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDtoOut> getAllpersons() {
        return personMapper.listToDTOs(personRepository.findAll());
    }

    @Override
    public PersonDtoOut getPerson(long id) {
        Optional<PersonEntity> fetchedPerson = personRepository.findById(id);
    //    if (fetchedPerson.isPresent())
    //        return personMapper.toDtoOut(fetchedPerson.get());
    //    throw new EntityNotFoundException("User id: " + id + " wasn't found in the database...");
        return personMapper.toDtoOut(fetchPersonById(id, "from PersonService"));
    }

    @Override
    public PersonDtoOut addPerson(PersonDtoIn personDTOin) {
        PersonEntity newPerson = personRepository.save(personMapper.toPersonEntity(personDTOin));
        return personMapper.toDtoOut(newPerson);
    }

    @Override
    public PersonDtoOut updatePerson(long id, PersonDtoIn personDTOin) {
        PersonEntity newEntity = new PersonEntity();
        personMapper.updateEntity(newEntity, personDTOin);
        newEntity.setId(id);
        PersonEntity requiredPerson = fetchPersonById(id, "during update from PersonService");
        List<LoanEntity> personsLoans = requiredPerson.getUserLoans();
        newEntity.setUserLoans(personsLoans);   //  only for PersonDtoOut after updating...
        return personMapper.toDtoOut(personRepository.save(newEntity));
    }

    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<PersonDtoBorrowedCar> borrowedCars(long id) {
        List<LoanEntity> loansByPersonId = loanRepository.getAllByUser(fetchPersonById(id, "borrowedCars from PersonService"));
        List<CarEntity> carsByPersonId = loansByPersonId.stream()
                .map(LoanEntity::getCar)
                .collect(Collectors.toList());
        return personMapper.toListDtoBorrowedCar(carsByPersonId, loansByPersonId);
    }

}
