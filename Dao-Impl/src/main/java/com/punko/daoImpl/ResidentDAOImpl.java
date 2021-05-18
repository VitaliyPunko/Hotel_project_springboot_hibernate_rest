package com.punko.daoImpl;


import com.punko.ResidentDAO;
import com.punko.entity.Apartment;
import com.punko.entity.Resident;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ResidentDAOImpl implements ResidentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Resident> getAllResident() {
        Session session = entityManager.unwrap(Session.class);
//        сделать запрос через query и вынести в проперти
//        Query<Resident> query = session.createQuery(SQL_GET_ALL);
//        List<Resident> residentList = query.getResultList();
//        return residentList;
        Query query = session.createQuery("from Resident");
        List<Resident> residentList = query.getResultList();
        return residentList;
    }

    @Override
    public Resident getById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        Resident resident = session.get(Resident.class, id);
        return resident;
    }

    @Override
    public void saveResident(Resident resident) {
//        Session session = entityManager.unwrap(Session.class);
//        int number = resident.getApartment().getApartmentNumber();
//        List<Apartment> apartments = session.createQuery("from Apartment where apartmentNumber = :apartmentNumber")
//                .setParameter("apartmentNumber", number).getResultList();
//        Apartment apartment = apartments.get(0);
//        resident.setApartment(apartment);
//        session.saveOrUpdate(resident);
    }


    @Override
    public void deleteResident(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from Resident where residentId = :residentId");
        query.setParameter("residentId", id);
        query.executeUpdate();
    }

    @Override
    public List<Apartment> getAllApartmentNumber() {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public List<Resident> findAllByTime(LocalDate arrivalTime, LocalDate departureTime) {
        return null;
    }
}
