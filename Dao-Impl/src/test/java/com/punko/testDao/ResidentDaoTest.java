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
        entityManager.persist(resident);
        List<Resident> residentList = residentDAO.getAllResident();
        Assertions.assertNotNull(residentList);
        Assertions.assertTrue(residentList.size() > 0);
    }
}
