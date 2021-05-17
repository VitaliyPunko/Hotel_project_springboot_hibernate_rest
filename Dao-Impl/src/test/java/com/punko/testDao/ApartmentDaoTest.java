package com.punko.testDao;

import com.punko.ApartmentDAO;
import com.punko.entity.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;


@DataJpaTest
//@ExtendWith({SpringExtension.class})
//@Import({ApartmentDAOImpl.class})
@ContextConfiguration(classes = TestConfig.class)
//@PropertySource({"classpath:application.properties"})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApartmentDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentDaoTest.class);


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApartmentDAO apartmentDAO;

    @Test
    public void shouldReturnAllApartmentTest() {
        LOGGER.debug("should find all apartments()");
        entityManager.persist(new Apartment(100, "CHEAP"));
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);
    }

    @Test
    public void shouldFindByIdApartmentTest() {
        entityManager.persist(new Apartment(100, "CHEAP"));
        entityManager.persist(new Apartment(101, "MEDIUM"));
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);

        Apartment apartment = apartmentDAO.getById(apartmentList.get(1).getApartmentId());
        Assertions.assertNotNull(apartment);
        Assertions.assertEquals(apartment.getApartmentNumber(), apartmentList.get(1).getApartmentNumber());
    }

    @Test
    public void shouldSaveApartmentTest() {
        apartmentDAO.saveApartment(new Apartment(100, "CHEAP"));
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);
    }

    @Test
    public void shouldUpdateApartmentTest() {
        Apartment apartment = new Apartment(100, "CHEAP");
        entityManager.persist(apartment);
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);

        apartment.setApartmentNumber(200);
        apartment.setApartmentClass("MEDIUM");
        apartmentDAO.saveApartment(apartment);

        Assertions.assertEquals(apartmentDAO.getById(apartment.getApartmentId()).getApartmentNumber(), apartment.getApartmentNumber());
        Assertions.assertEquals(apartment, apartmentDAO.getById(apartment.getApartmentId()));
    }

    @Test
    public void shouldDeleteApartmentTest() {
        entityManager.persist(new Apartment(100, "CHEAP"));
        entityManager.persist(new Apartment(105, "MEDIUM"));

        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);

        long beforeDelete = apartmentDAO.count();
        apartmentDAO.delete(apartmentList.get(0).getApartmentId());
        long afterDelete = apartmentDAO.count();

        Assertions.assertEquals(beforeDelete, afterDelete + 1);
    }

    @Test
    public void shouldReturnCountOfApartmentTest() {
        entityManager.persist(new Apartment(100, "CHEAP"));
        entityManager.persist(new Apartment(105, "CHEAP"));
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);

        Long count = apartmentDAO.count();
        Assertions.assertTrue(count > 0);
        Assertions.assertEquals((long) count, apartmentList.size());
    }


}
