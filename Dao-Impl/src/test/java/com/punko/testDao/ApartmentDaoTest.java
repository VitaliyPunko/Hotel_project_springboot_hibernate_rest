package com.punko.testDao;

import com.punko.ApartmentDAO;
import com.punko.entity.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApartmentDAO apartmentDAO;

    @Test
    public void shouldReturnAllApartmentTest() {
        entityManager.persist(new Apartment(100, "CHEAP"));
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);
    }

//    @Test
//    public void shouldReturnAllApartmentTest() {
//
//        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
//        Assertions.assertNotNull(apartmentList);
//        Assertions.assertTrue(apartmentList.size() > 0);
//    }

}
