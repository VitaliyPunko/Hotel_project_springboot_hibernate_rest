package com.punko.testDao;

import com.punko.ResidentDAO;
import com.punko.entity.Apartment;
import com.punko.entity.Resident;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class ResidentDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentDaoTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ResidentDAO residentDAO;

    @Test
    public void shouldReturnAllResidentTest() {
        LOGGER.debug("should find all resident()");
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);
        entityManager.persist(apartment);
        entityManager.persist(resident);
        List<Resident> residentList = residentDAO.getAllResident();
        Assertions.assertNotNull(residentList);
        Assertions.assertTrue(residentList.size() > 0);
    }

    @Test
    public void shouldReturnAllResidentByTimeTest() {
        LOGGER.debug("should find all resident by time()");
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        Resident resident2 = new Resident("Stephen2", "King2", "stephenking2@test.com",
                LocalDate.of(2021, 3, 23),
                LocalDate.of(2021, 5, 30));

        resident.setApartment(apartment);
        resident2.setApartment(apartment);

        entityManager.persist(apartment);
        entityManager.persist(resident);
        entityManager.persist(resident2);

        LocalDate arrivalTime = LocalDate.of(2021, 3, 3);
        LocalDate departureTime = LocalDate.of(2021, 4, 13);

        List<Resident> residentList = residentDAO.findAllByTime(arrivalTime, departureTime);
        Assertions.assertNotNull(residentList);
        Assertions.assertTrue(residentList.size() > 0);

    }

    @Test
    public void shouldReturnResidentByIdTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);
        entityManager.persist(apartment);
        entityManager.persist(resident);
        Assertions.assertNotNull(residentDAO.getById(resident.getResidentId()));
        Assertions.assertEquals(residentDAO.getById(resident.getResidentId()).getFirstName(), resident.getFirstName());
    }

    @Test
    public void shouldReturnExceptionResidentByIdTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);
        entityManager.persist(apartment);
        entityManager.persist(resident);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            residentDAO.getById(999);
        });
    }

    @Test
    public void shouldCreateResidentTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);
        entityManager.persist(apartment);
        long countBefore = residentDAO.count();
        entityManager.persist(resident);
        long countAfter = residentDAO.count();
        Assertions.assertEquals(countBefore + 1, countAfter);
    }


    @Test
    public void shouldThrowExceptionCreateWithWrongParamResidentTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);

        entityManager.persist(apartment);
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(resident);
        });
    }

    @Test
    public void shouldThrowExceptionCreateWithTheSameEmailResidentTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        entityManager.persist(apartment);
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);

        Resident resident2 = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident2.setApartment(apartment);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            residentDAO.saveResident(resident);
            residentDAO.saveResident(resident2);
        });
    }

    @Test
    public void shouldUpdateResidentTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);
        entityManager.persist(apartment);
        entityManager.persist(resident);

        resident.setFirstName("John");
        System.out.println(resident);

        residentDAO.saveResident(resident);
        Assertions.assertEquals(residentDAO.getById(resident.getResidentId()).getFirstName(), resident.getFirstName());
        Assertions.assertEquals(residentDAO.getById(resident.getResidentId()).getApartment(), resident.getApartment());
    }


    @Test
    public void shouldDeleteResident() {
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);
        entityManager.persist(apartment);
        entityManager.persist(resident);

        Resident resident2 = new Resident("John", "Smith", "smith@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident2.setApartment(apartment);
        entityManager.persist(resident2);

        List<Resident> residentList = residentDAO.getAllResident();
        Assertions.assertNotNull(residentList);
        Assertions.assertTrue(residentList.size() > 0);

        long beforeDelete = residentDAO.count();
        residentDAO.deleteResident(residentList.get(0).getResidentId());
        long afterDelete = residentDAO.count();

        Assertions.assertEquals(beforeDelete, afterDelete + 1);
    }

    @Test
    public void shouldReturnCountOfResidentTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        Resident resident = new Resident("Stephen", "King", "stephenking@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident.setApartment(apartment);
        entityManager.persist(apartment);
        entityManager.persist(resident);

        Resident resident2 = new Resident("John", "Smith", "smith@test.com",
                LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23));
        resident2.setApartment(apartment);
        entityManager.persist(resident2);

        List<Resident> residentList = residentDAO.getAllResident();
        Assertions.assertNotNull(residentList);
        Assertions.assertTrue(residentList.size() > 0);

        Long count = residentDAO.count();
        Assertions.assertTrue(count > 0);
        Assertions.assertEquals((long) count, residentList.size());
    }

}
