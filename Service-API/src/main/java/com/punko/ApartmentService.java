package com.punko;


import com.punko.entity.Apartment;

import java.util.List;

public interface ApartmentService {

    List<Apartment> getAllApartment();

    void save(Apartment apartment);

    Apartment getById(Integer id);

    void delete(Integer id);

    Long count();
}
