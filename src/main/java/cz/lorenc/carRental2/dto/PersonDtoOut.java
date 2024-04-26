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
public class PersonDtoOut {

    private long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String city;

    private int age;

    private List<LoanEntity> userLoans;

}
