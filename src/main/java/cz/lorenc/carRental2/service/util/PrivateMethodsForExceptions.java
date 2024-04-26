package cz.lorenc.carRental2.service.util;

import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import cz.lorenc.carRental2.entity.repositories.CarRepository;
import cz.lorenc.carRental2.entity.repositories.LoanRepository;
import cz.lorenc.carRental2.entity.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;

public class PrivateMethodsForExceptions {

    protected PersonRepository personRepository;

    protected LoanRepository loanRepository;

    protected CarRepository carRepository;

    public PrivateMethodsForExceptions( PersonRepository personRepository, LoanRepository loanRepository, CarRepository carRepository) {
        this.personRepository = personRepository;
        this.loanRepository = loanRepository;
        this.carRepository = carRepository;
    }

    protected PersonEntity fetchPersonById(long id, String message) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EntityNotFoundEx fetchPerson called from PrivMethodsForEx, which was called from " + message));
    }
    protected LoanEntity fetchLoanById(long id, String message) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EntityNotFoundEx fetchLoan called from PrivMethodsForEx, which was called from " + message));
    }

    protected CarEntity fetchCarById(long id, String message) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EntityNotFoundEx fetchCar called from PrivMethodsForEx, which was called from " + message));
    }
}
