package cz.lorenc.carRental2.dto;

import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDtoOut {

    private long id;

    private LocalDate rentalDate;

    private LocalDate returnDate;

    private int kilometersDriven;

    private PersonEntity user;

    private CarEntity car;

}
