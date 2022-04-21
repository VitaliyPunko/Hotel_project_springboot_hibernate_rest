package com.punko.daoImpl;

import com.punko.ApartmentDAO;
import com.punko.entity.Apartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ApartmentDAOImpl implements ApartmentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentDAOImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Apartment> getAllApartment() {
        Query query = entityManager.createQuery("from Apartment");
        List<Apartment> apartmentList = query.getResultList();
        return apartmentList;
    }

    @Override
    public Apartment getById(Integer apartmentId) {
        if (!isApartmentIdCorrect(apartmentId)) {
            throw new IllegalArgumentException("Apartment with this id doesn't exist: " + apartmentId);
        }
        Apartment apartment = entityManager.find(Apartment.class, apartmentId);
        return apartment;
    }

    @Override
    public void saveApartment(Apartment apartment) {
        if (!isNumberTheSameButDifferentClass(apartment)) {
            LOGGER.warn("Apartment with that number is already exist: {}", apartment);
            throw new IllegalArgumentException("Apartment with number = " + apartment.getApartmentNumber() + " is already exist");
        }
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(apartment);
        Apartment newApartment = entityManager.merge(apartment);
        apartment.setApartmentId(newApartment.getApartmentId());
    }

    @Override
    public void delete(Integer apartmentId) {
        if (!isApartmentIdCorrect(apartmentId)) {
            throw new IllegalArgumentException("Apartment with this id doesn't exist: " + apartmentId);
        }
        Query query = entityManager.createQuery("delete from Apartment where id = :apartmentId");
        query.setParameter("apartmentId", apartmentId);
        query.executeUpdate();
    }

    @Override
    public Long count() {
        Query query = entityManager.createQuery("select count(*) from Apartment");
        return (Long) ((org.hibernate.query.Query<?>) query).uniqueResult();
    }

    private boolean isApartmentIdCorrect(int id) {
        List<Apartment> apartmentList = getAllApartment();
        List<Integer> integerList = new ArrayList<>(apartmentList.size());
        for (Apartment apartment : apartmentList) {
            integerList.add(apartment.getApartmentId());
        }
        if (!integerList.contains(id)) {
            return false;
        }
        return true;
    }

    private boolean isTheNumberUnique(Apartment apartment) {
        Query query = entityManager.createQuery("select count(apartmentId) from Apartment where apartmentNumber = :apartmentNumber");
        query.setParameter("apartmentNumber", apartment.getApartmentNumber());
        return (Long) ((org.hibernate.query.Query<?>) query).uniqueResult() == 0;
    }

    private boolean isNumberTheSameButDifferentClass(Apartment apartment) {
        if (apartment.getApartmentId() == null) return isTheNumberUnique(apartment);
        Apartment apartmentFromDB = getById(apartment.getApartmentId());
        if (apartmentFromDB.getApartmentNumber().equals(apartment.getApartmentNumber())) {
            return true;
        }
        return isTheNumberUnique(apartment);
    }
}
