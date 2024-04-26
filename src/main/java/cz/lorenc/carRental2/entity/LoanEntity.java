package cz.lorenc.carRental2.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name="car_loan")
@Getter
@Setter
@NoArgsConstructor
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private LocalDate rentalDate;

    @Column()
    private LocalDate returnDate;

    @Column()
    private int kilometersDriven;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne
    private PersonEntity user;  // if I don't put here user when I create or update any loan, that he will miss in the
                                // List<LoanEntity> userLoans in PersonEntity

//    @JsonIgnore                 // it will be applied for mapping from PersonEntity (we don't want it...) and also form CarEntity (we want it...)
    @JsonInclude(JsonInclude.Include.NON_NULL) // if car = null then won't be included to the Json... we can set it as null from CarEntity...
    @ManyToOne
    private CarEntity car;

       @Override
    public String toString() {
        return String.format("LoanEntity id: %s, user:  %s, car: %s", id, getUser().getName(), getCar().getSpz());
    }
}

//    @ManyToOne    // this is variant for only one side binding between entities. It creates attribute in table car_id
//                  // (entity doesn't have this attribute) we will need our own getter getCarId for LoanMapper
//    @JoinColumn(name = "car_id") // nullable = false -> initial value...
//    private CarEntity car;
//
//    public long getCarId() { // LoanEntity doesn't have car_id, but we need create getter for LoanMapper...
//        if (car !=null) {
//            return car.getId();
//        } else return  0L;
//    }
