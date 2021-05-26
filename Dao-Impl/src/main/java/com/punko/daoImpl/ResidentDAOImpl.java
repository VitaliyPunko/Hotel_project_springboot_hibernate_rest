package com.punko.daoImpl;


import com.punko.ResidentDAO;
import com.punko.entity.Resident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResidentDAOImpl implements ResidentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResidentDAOImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Resident> getAllResident() {
//        сделать запрос через query и вынести в проперти
//        Query<Resident> query = session.createQuery(SQL_GET_ALL);
//        List<Resident> residentList = query.getResultList();
//        return residentList;
        Query query = entityManager.createQuery("from Resident");
        List<Resident> residentList = query.getResultList();
        return residentList;
    }

    @Override
    public Resident getById(Integer id) {
        if (!isResidentIdCorrect(id)) {
            throw new IllegalArgumentException("Resident with this id doesn't exist: " + id);
        }
        Resident resident = entityManager.find(Resident.class, id);
        return resident;
    }

    @Override
    public void saveResident(Resident resident) {
        if (!isItTheSameEmail(resident)) {
            throw new IllegalArgumentException("Resident with this email " + resident.getEmail() + " already exist");
        }
        if (!isArrivalTimeBeforeDepartureTime(resident)) {
            throw new IllegalArgumentException("Arrival time (" + resident.getArrivalTime() + ") should be before than Departure time: (" + resident.getDepartureTime() + ")");
        }
//        int number = resident.getApartment().getApartmentNumber();
//        List<Apartment> apartments = session.createQuery("from Apartment where apartmentNumber = :apartmentNumber")
//                .setParameter("apartmentNumber", number).getResultList();
//        Apartment apartment = apartments.get(0);
//        resident.setApartment(apartment);
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(resident);
        Resident newResident = entityManager.merge(resident);
        resident.setResidentId(newResident.getResidentId());
    }


    @Override
    public void deleteResident(Integer id) {
        if (!isResidentIdCorrect(id)) {
            throw new IllegalArgumentException("Resident with this id doesn't exist: " + id);
        }
        Query query = entityManager.createQuery("delete from Resident where residentId = :residentId");
        query.setParameter("residentId", id);
        query.executeUpdate();
    }

    @Override
    public Long count() {
        Query query = entityManager.createQuery("select count(*) from Resident");
        return (Long) ((org.hibernate.query.Query<?>) query).uniqueResult();
    }

    @Override
    public List<Resident> findAllByTime(LocalDate arrival, LocalDate departure) {
        if (departure.isBefore(arrival)) {
            throw new IllegalArgumentException("Arrival time (" + arrival +
                    ") should be before than Departure time: (" + departure + ")");
        }
        Query query = entityManager.createQuery("from Resident where arrivalTime >= :arrivalTime and " +
                "departureTime <= :departureTime");
        query.setParameter("arrivalTime", arrival);
        query.setParameter("departureTime", departure);
        return query.getResultList();
    }

    private boolean isResidentIdCorrect(int id) {
        List<Resident> residentList = getAllResident();
        List<Integer> integerList = new ArrayList<>(residentList.size());
        for (Resident resident : residentList) {
            integerList.add(resident.getResidentId());
        }
        if (!integerList.contains(id)) {
            LOGGER.debug("isResidentIdCorrect method. Resident with this id doesn't exist: {}", id);
            return false;
        }
        return true;
    }

    private boolean isEmailUnique(Resident resident) {
        LOGGER.debug("check is resident email unique: {}", resident);
        Query query = entityManager.createQuery("select count(residentId) from Resident " +
                "where email = :email");
        query.setParameter("email", resident.getEmail());
        return (Long) ((org.hibernate.query.Query<?>) query).uniqueResult() == 0;
    }

    private boolean isItTheSameEmail(Resident resident) {
        LOGGER.debug("check for update the same email unique: {}", resident);
        if (resident.getResidentId() == null) return isEmailUnique(resident);
        String emailFromDB = getById(resident.getResidentId()).getEmail();
        if (emailFromDB.equals(resident.getEmail())) {
            return true;
        } else {
            return isEmailUnique(resident);
        }
    }

    private boolean isArrivalTimeBeforeDepartureTime(Resident resident) {
        return resident.getArrivalTime().isBefore(resident.getDepartureTime());
    }
}
