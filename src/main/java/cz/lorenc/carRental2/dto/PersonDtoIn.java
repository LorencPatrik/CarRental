package cz.lorenc.carRental2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDtoIn {

    private String name;

    private String address;

    private String phoneNumber;

    private String city;

    private int age;

}
