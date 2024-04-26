package cz.lorenc.carRental2.dto;

import cz.lorenc.carRental2.entity.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDtoOut {

    private long id;

    private String type;

    private String spz;

    private int yearOfManufacture;

    private int kilometersStatus;

    private List<LoanEntity> carLoans;

}
