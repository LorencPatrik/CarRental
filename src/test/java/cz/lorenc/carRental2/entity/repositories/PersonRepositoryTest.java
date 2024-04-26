package cz.lorenc.carRental2.entity.repositories;



import cz.lorenc.carRental2.entity.PersonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yaml")
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

//    @BeforeEach
//    public void beforeEach() {
//        PersonEntity newEntity = new PersonEntity();
//        newEntity.setName("George Thompson");
//        newEntity.setAge(32);
//        newEntity.setAddress("dlouhá 35");
//        newEntity.setCity("Praha");
//        newEntity.setPhoneNumber("+420608656669");
//        personRepository.save(newEntity);
//    }
//
//    @Test
//    public void testFindAll() {
//        System.out.println("in the testFindAll");
//        System.out.println(personRepository.findById(1L));
//        List<PersonEntity> entities = personRepository.findAll();
//        assertEquals(1, entities.size());
//    }

//    @Test
//    public void testFindById() {
//        PersonEntity entity = personRepository.findById(1L).orElse(null);
//    }

//    @Test
//    public void testSave() {
//        PersonEntity entity = new PersonEntity();
//        // ...
//        personRepository.save(entity);
//        // ... testy
//    }

//    @Test
//    public void testDelete() {
//        personRepository.deleteById(1L);
//        // ... testy
//    }

}