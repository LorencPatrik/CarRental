package cz.lorenc.carRental2.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String city;

    @Column()
    private int age;

//    @JsonIgnore // it won't be used if we need mapping from this entity
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "user")
    private List<LoanEntity> userLoans; // an individual list for each entity where is the same userId
                                        // only for reading. It isn't possible to change user in lonas of this side of @OneToMany...

    @Override
    public String toString() {
        return String.format("PersonEntity id: %s, name: %s", id, name);
    }
}
