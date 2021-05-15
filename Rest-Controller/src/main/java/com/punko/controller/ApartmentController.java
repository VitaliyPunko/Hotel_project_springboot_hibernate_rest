package com.punko.controller;

import com.punko.ApartmentService;
import com.punko.entity.Apartment;
import com.punko.exceptions.ApartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/apartments")
    public List<Apartment> getAllApartments() {
        return apartmentService.getAllApartment();
    }

    @GetMapping("/apartments/{apartmentId}")
    public Apartment getApartmentById(@PathVariable int apartmentId) {
        Apartment apartment = apartmentService.getById(apartmentId);
        if (apartment == null) {
            throw new ApartmentException("Apartment with this id doesn't exist " + apartmentId);
        }
        return apartment;
    }

    @PostMapping("/apartments")
    public Apartment addNewApartment(@RequestBody Apartment apartment) {
        apartmentService.save(apartment);
        return apartment;
    }

    @PutMapping("/apartments")
    public Apartment updateApartment(@RequestBody Apartment apartment) {
        apartmentService.save(apartment);
        return apartment;
    }

    @DeleteMapping("/apartments/{apartmentId}")
    public String deleteApartment(@PathVariable int apartmentId) {
        if (apartmentService.getById(apartmentId) == null) {
            throw new ApartmentException("Apartment with this id doesn't exist " + apartmentId);
        }
        apartmentService.delete(apartmentId);
        return "Apartment with id = " + apartmentId + " was deleted";
    }

}
