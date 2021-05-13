package com.punko.controller;

import com.punko.ApartmentService;
import com.punko.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/apartments")
    public List<Apartment> getAllApartments() {
        List<Apartment> apartmentList = apartmentService.getAllApartment();
        return apartmentList;
    }

    @GetMapping("/apartments/{id}")
    public Apartment getApartmentById(@PathVariable int id) {
        Apartment apartment = apartmentService.getById(id);
        return apartment;
    }
}
