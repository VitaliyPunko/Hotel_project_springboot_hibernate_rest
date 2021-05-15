//package com.punko.testDao;
//
//import com.punko.ApartmentDAO;
////import com.punko.config.SpringBootApplicationConfig;
//import com.punko.daoImpl.ApartmentDAOImpl;
//import com.punko.entity.Apartment;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//
//@DataJpaTest
//@ExtendWith({SpringExtension.class})
//@Import({ApartmentDAOImpl.class})
////@ContextConfiguration(classes = SpringBootApplicationConfig.class)
//public class ApartmentDaoTest {
//
////    @Autowired
////    private TestEntityManager entityManager;
//
//    @Autowired
//    private ApartmentDAO apartmentDAO;
//
//    @Test
//    public void shouldReturnAllApartmentTest() {
//
//        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
//        Assertions.assertNotNull(apartmentList);
//        Assertions.assertTrue(apartmentList.size() > 0);
//
//
//    }
//}
