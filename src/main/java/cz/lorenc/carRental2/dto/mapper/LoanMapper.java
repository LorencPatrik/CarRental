package cz.lorenc.carRental2.dto.mapper;

import cz.lorenc.carRental2.dto.LoanDtoIn;
import cz.lorenc.carRental2.dto.LoanDtoOut;
import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    List<LoanDtoOut> listToDTO(List<LoanEntity> source);

    LoanDtoOut toDtoOut(LoanEntity source);

    @Mapping(target = "userLoans", ignore = true) // we will force to change PersonEntity (user) during the mapping from LoanEntity to LoanDTOout
    PersonEntity toEntity(PersonEntity source); // whenever this Entity is read during mapping, this function will be used!

    @Mapping(target = "carLoans", ignore = true)
    CarEntity toEntity(CarEntity source);

    LoanEntity toEntity(LoanDtoIn source);

    //@Mapping( target = "user", ignore = true) // LoanDTOin doesn't include user attribute (it has only personId...)
    // it was my mistake, because user with @ManyToOne isn't attribute for mapping...
    LoanEntity updateEntity(@MappingTarget LoanEntity target, LoanDtoIn source);

}
