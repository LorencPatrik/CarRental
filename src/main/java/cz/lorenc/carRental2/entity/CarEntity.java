package cz.lorenc.carRental2.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "car")
@Getter
@Setter
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String spz;

    @Column()
    private int yearOfManufacture;

    @Column()
    private int kilometersStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "car") // fetch = FetchType.EAGER all carLoans will be read from database immediately
    private List<LoanEntity> carLoans;

    @Override
    public String toString() {
        return String.format("CarEntity id: %s, type: %s, spz: %s", id, type, spz);
    }
}
