package cz.lorenc.carRental2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDtoIn {

    private String type;

    private String spz;

    private int yearOfManufacture;

    private int kilometersStatus;

}
