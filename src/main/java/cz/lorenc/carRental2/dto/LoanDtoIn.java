package cz.lorenc.carRental2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDtoIn {

//    private long id;  // we send id for update separately and for creating a new loan we don't need id attribute

    private LocalDate rentalDate;

    private LocalDate returnDate;

    private int kilometersDriven;

    private long userId; // the name according the table

    private long carId; // --- // ---

}
