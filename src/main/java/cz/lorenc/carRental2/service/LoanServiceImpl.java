package cz.lorenc.carRental2.service;

import cz.lorenc.carRental2.dto.LoanDtoIn;
import cz.lorenc.carRental2.dto.LoanDtoOut;
import cz.lorenc.carRental2.dto.mapper.LoanMapper;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.repositories.CarRepository;
import cz.lorenc.carRental2.entity.repositories.LoanRepository;
import cz.lorenc.carRental2.entity.repositories.PersonRepository;
import cz.lorenc.carRental2.service.util.PrivateMethodsForExceptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl extends PrivateMethodsForExceptions implements LoanService{

    LoanMapper loanMapper;

    public LoanServiceImpl(
            PersonRepository personRepository,
            LoanRepository loanRepository,
            CarRepository carRepository,
            LoanMapper loanMapper
    ) {
        super(personRepository, loanRepository, carRepository);
        this.loanMapper = loanMapper;
    }

    @Override
    public List<LoanDtoOut> getAll() {
        return loanMapper.listToDTO(loanRepository.findAll());
    }

    @Override
    public LoanDtoOut getLoan(long id) {
    //    return loanMapper.toDtoOut(loanRepository.findById(id).get()); // this may trigger a local @ExceptionHandler in the LoanController...
        return loanMapper.toDtoOut(fetchLoanById(id, "getLoan from LoanServiceImpl")); // this is the correct way how to do it...
    }

    @Override
    public LoanDtoOut addLoan(LoanDtoIn loanDTOin) {
        LoanEntity newEntity = loanMapper.toEntity(loanDTOin);
        newEntity.setUser(fetchPersonById(loanDTOin.getUserId(),"addLoan fetchPerson from LoanServiceImpl")); // getReferenceById isn't enough here!
        newEntity.setCar(fetchCarById(loanDTOin.getCarId(), "addLoan fetchCar from LoanServiceImpl"));
        LoanEntity savedEntity = loanRepository.save(newEntity);
        return loanMapper.toDtoOut(savedEntity);
    }

    @Override
    public LoanDtoOut updateLoan(long id, LoanDtoIn loanDTOin) {
        LoanEntity updateEntity = loanMapper.updateEntity(new LoanEntity(), loanDTOin); // at first get the entity from database if it exists
        updateEntity.setUser(fetchPersonById(loanDTOin.getUserId(), "updateLoan fetchPerson from LoanServiceImpl"));   // it isn't possible to change user in List in the PersonEntity (at @OneToMany - side)
        updateEntity.setCar(fetchCarById(loanDTOin.getCarId(), "updateLoan fetchCar from LoanServiceImpl"));
        updateEntity.setId(id);
        return loanMapper.toDtoOut(loanRepository.save(updateEntity));
    }

    @Override
    public void deleteLoan(long id) {
        loanRepository.deleteById(id);
    }

//     region: Private methods - we are extending the private methods from PrivateMethodsForExceptions...
//
//    private LoanEntity fetchLoanById(long id) {
//        return loanRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("The loan with id: " + id + " wasn't found in the database"));
//    }
//
//     endregion
}
