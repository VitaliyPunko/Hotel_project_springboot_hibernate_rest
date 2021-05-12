package com.punko.daoImpl;

import com.punko.ApartmentDAO;
import com.punko.entity.Apartment;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ApartmentDAOImpl implements ApartmentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Apartment> getAllApartment() {
        Session session = entityManager.unwrap(Session.class);
//        List<Apartment> apartmentList = session.createQuery("from Apartment", Apartment.class).getResultList();
        Query query = session.createQuery("from Apartment");
        List<Apartment> apartmentList = query.getResultList();
        return apartmentList;
    }

//    @Override
//    public void saveApartment(Apartment apartment) {
//        Session session = sessionFactory.getCurrentSession();
//        session.saveOrUpdate(apartment);
//    }
//
//    @Override
//    public Apartment getById(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        Apartment apartment = session.get(Apartment.class, id);
//        return apartment;
//    }
//
//    @Override
//    public void delete(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        Query<Apartment> query = session.createQuery("delete from Apartment where id = :apartmentId");
//        query.setParameter("apartmentId", id);
//        query.executeUpdate();
//    }
}
