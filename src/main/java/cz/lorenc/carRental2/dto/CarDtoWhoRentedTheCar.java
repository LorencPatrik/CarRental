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
public class CarDtoWhoRentedTheCar {

    private String name;

    private String address;

    private String city;

    private LocalDate rentalDate;

    private LocalDate returnDate;

    private int kilometersDriven;

}
