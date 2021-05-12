package com.punko.daoImpl;


import com.punko.ResidentDAO;
import com.punko.entity.Resident;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
//now
        List<Resident> residentList = session.createQuery("from Resident", Resident.class).getResultList();
        return residentList;
    }

    /**
     * Get apartmentNumber from user,
     * then get Apartment by this number,
     * and set Apartment to resident
     //     */
//    @Override
//    public void saveResident(Resident resident) {
//        Session session = sessionFactory.getCurrentSession();
//        int number = resident.getApartment().getApartmentNumber();
//        List<Apartment> apartments = session.createQuery("from Apartment where apartmentNumber = :apartmentNumber")
//                .setParameter("apartmentNumber", number).getResultList();
//        Apartment apartment = apartments.get(0);
//        resident.setApartment(apartment);
//        session.saveOrUpdate(resident);
//    }
//
//    @Override
//    public Resident getById(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        Resident resident = session.get(Resident.class, id);
//        return resident;
//    }
//
//    @Override
//    public void deleteResident(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        Query<Resident> query = session.createQuery("delete from Resident where residentId = :residentId");
//        query.setParameter("residentId", id);
//        query.executeUpdate();
//    }
}
