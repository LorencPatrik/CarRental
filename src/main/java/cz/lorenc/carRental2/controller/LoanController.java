package cz.lorenc.carRental2.controller;

import cz.lorenc.carRental2.dto.LoanDtoIn;
import cz.lorenc.carRental2.dto.LoanDtoOut;
import cz.lorenc.carRental2.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleExceptions(Exception e) {
        System.out.println("An error was occurred in the loan Controller: " + e.toString());
    }

    @ExceptionHandler(NoSuchElementException.class)   // if we have ExceptionHandler in a controller it will be used preferably (instead of the ExceptionAdvice...)
    @ResponseStatus(HttpStatus.NOT_FOUND)               // it returns http status 404 for a client
    public void handleNoSuchElementException(Exception e) {
        System.out.println("The NoSuchElementException was occurred in the loan Controller: " + e.toString());
    }

    @GetMapping("/getAll")
    List<LoanDtoOut> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/getLoan/{id}")
    LoanDtoOut getLoan(@PathVariable("id") Long id) {
        return loanService.getLoan(id); // this method uses the wrong database access... this may trigger a local exception...
    }

    @PostMapping("/addLoan")
    LoanDtoOut addLoan(@RequestBody LoanDtoIn loanDTOin) {
        return loanService.addLoan(loanDTOin);
    }

    @PutMapping("/updateLoan/{id}")
    LoanDtoOut updateLoan(@PathVariable("id") Long id, @RequestBody LoanDtoIn loanDTOin) {
        return loanService.updateLoan(id, loanDTOin);
    }

    @DeleteMapping("/deleteLoan/{id}")
    void deleteLoan(@PathVariable("id") Long id) {
        loanService.deleteLoan(id);
    }

}
