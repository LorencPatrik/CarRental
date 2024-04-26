package cz.lorenc.carRental2.dto.mapper;

import cz.lorenc.carRental2.dto.PersonDtoIn;
import cz.lorenc.carRental2.dto.PersonDtoOut;
import cz.lorenc.carRental2.dto.PersonDtoBorrowedCar;
import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDtoOut toDtoOut(PersonEntity source);

    List<LoanEntity> toListDtoOut(List<LoanEntity> source); // we need this method for forcing generated sources use our toEntity with ignore attributes
                                                            // because those attributes are in the list<LoanEntity> userLoans
    @Mapping(target = "user", ignore = true)
    LoanEntity toLoanEntity(LoanEntity source);

    PersonEntity toPersonEntity(PersonDtoIn source);

    @Mapping(target = "carLoans", ignore = true)   // we don't need to use List<PersonEntity> here because userLoans is directly in this entity
    CarEntity toCarEntity(CarEntity source);

    void updateEntity(@MappingTarget PersonEntity target, PersonDtoIn source);

    List<PersonDtoOut> listToDTOs(List<PersonEntity> source);

    // we can't use @Mapping(target..., source...) for mapping from List to List...
    default List<PersonDtoBorrowedCar> toListDtoBorrowedCar(List<CarEntity> carSource, List<LoanEntity> loanSource) {
        List<PersonDtoBorrowedCar> newList = new ArrayList<>();
        Iterator<CarEntity> carIterator = carSource.iterator();
        Iterator<LoanEntity> loanIterator = loanSource.iterator();
        while (carIterator.hasNext() && loanIterator.hasNext()) {
            PersonDtoBorrowedCar newDto = toDtoBorrowedCar(carIterator.next(), loanIterator.next());
            newList.add(newDto);
        }
        return newList;
    }

    // but we can use @Mapping for the same dto and entity without List in the next method, which will call this method in implementation itself...
    @Mapping(target = "type", source = "carSource.type")    // this method will be working well without @Mapping annotations
    @Mapping(target = "spz", source = "carSource.spz")
    @Mapping(target = "kilometersDriven", source = "loanSource.kilometersDriven")
    @Mapping(target = "rentalDate", source = "loanSource.rentalDate")
    @Mapping(target = "returnDate", source = "loanSource.returnDate")
    PersonDtoBorrowedCar toDtoBorrowedCar(CarEntity carSource, LoanEntity loanSource);

}

