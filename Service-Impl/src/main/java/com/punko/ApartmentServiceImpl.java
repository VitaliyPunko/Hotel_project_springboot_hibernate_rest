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

//    @Override
//    public void saveApartment(Apartment apartment) {
//
//    }
//
//    @Override
//    public Apartment getById(Integer id) {
//        return null;
//    }
//
//    @Override
//    public void delete(Integer id) {
//
//    }


}