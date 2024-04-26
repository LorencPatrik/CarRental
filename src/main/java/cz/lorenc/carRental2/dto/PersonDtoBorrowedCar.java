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
public class PersonDtoBorrowedCar {

    private String type;

    private String spz;

    private int kilometersDriven;

    private LocalDate rentalDate;

    private LocalDate returnDate;

}
