package com.punko;


import com.punko.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    ApartmentDAO apartmentDAO;

    @Override
    public List<Apartment> getAllApartment() {
        return apartmentDAO.getAllApartment();
    }

    @Override
    public void save(Apartment apartment) {
        apartmentDAO.saveApartment(apartment);
    }

    @Override
    public Apartment getById(Integer id) {
        return apartmentDAO.getById(id);
    }

    @Override
    public String delete(Integer id) {
        apartmentDAO.delete(id);
        return "SUCCESS";
    }

    @Override
    public Long count() {
        return apartmentDAO.count();
    }


}
