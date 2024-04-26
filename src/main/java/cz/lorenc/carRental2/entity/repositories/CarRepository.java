package cz.lorenc.carRental2.entity.repositories;

import cz.lorenc.carRental2.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
