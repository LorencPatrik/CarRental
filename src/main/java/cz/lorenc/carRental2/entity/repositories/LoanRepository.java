package cz.lorenc.carRental2.entity.repositories;

import cz.lorenc.carRental2.entity.CarEntity;
import cz.lorenc.carRental2.entity.LoanEntity;
import cz.lorenc.carRental2.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> getAllByUser(PersonEntity user);
    List<LoanEntity> getAllByCar(CarEntity car);

}
