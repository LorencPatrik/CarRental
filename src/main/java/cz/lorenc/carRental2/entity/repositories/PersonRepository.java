package cz.lorenc.carRental2.entity.repositories;

import cz.lorenc.carRental2.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
