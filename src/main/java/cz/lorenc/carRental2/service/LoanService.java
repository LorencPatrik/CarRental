package cz.lorenc.carRental2.service;

import cz.lorenc.carRental2.dto.LoanDtoIn;
import cz.lorenc.carRental2.dto.LoanDtoOut;

import java.util.List;

public interface LoanService {

    List<LoanDtoOut> getAll();

    LoanDtoOut getLoan(long id);

    LoanDtoOut addLoan(LoanDtoIn loanDTOin);

    LoanDtoOut updateLoan(long id, LoanDtoIn LoanDTOin); // we need return back DTOout with attribute user...

    void deleteLoan(long id);

}
