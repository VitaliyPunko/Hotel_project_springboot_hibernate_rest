package com.punko;

import com.punko.entity.Apartment;
import com.punko.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    ResidentDAO residentDAO;

    @Override
    public List<Resident> getAllResident() {
        return residentDAO.getAllResident();
    }

    @Override
    public void saveResident(Resident resident) {
        residentDAO.saveResident(resident);
    }

    @Override
    public Resident getById(Integer id) {
        return residentDAO.getById(id);
    }

    @Override
    public void deleteResident(Integer id) {
        residentDAO.deleteResident(id);
    }

    @Override
    public List<Resident> findAllByTime(LocalDate arrivalTime, LocalDate departureTime) {
        return residentDAO.findAllByTime(arrivalTime, departureTime);
    }

    @Override
    public List<Apartment> getAllApartmentNumber() {
        return residentDAO.getAllApartmentNumber();
    }

    @Override
    public Long count() {
        return residentDAO.count();
    }
}
