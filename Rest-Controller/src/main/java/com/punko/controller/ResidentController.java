package com.punko.controller;

import com.punko.ResidentService;
import com.punko.entity.Resident;
import com.punko.exceptions.ApartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ResidentController {

    @Autowired
    ResidentService residentService;

    @GetMapping("/residents")
    public List<Resident> getAllResident() {
        List<Resident> residentList = residentService.getAllResident();
        return residentList;
    }

    @GetMapping("/residents/{id}")
    public Resident findResidentById(@PathVariable int id) {
        Resident resident = residentService.getById(id);
        if (resident == null) {
            throw new ApartmentException("Resident with this id doesn't exist " + id);
        }
        return resident;
    }

    @PostMapping("/residents")
    public Resident addNewResident(@RequestBody Resident resident) {
        residentService.saveResident(resident);
        return resident;
    }

    @PutMapping("/residents")
    public Resident updateResident(@RequestBody Resident resident) {
        residentService.saveResident(resident);
        return resident;
    }

    @DeleteMapping("/residents/{id}")
    public String deleteResident(@PathVariable int id) {
        if (residentService.getById(id) == null) {
            throw new ApartmentException("Resident with this id doesn't exist " + id);
        }
        residentService.deleteResident(id);
        return "Resident with id = " + id + " was deleted";
    }

    @GetMapping("/residents/count")
    public Long getCountOfResidents() {
        return residentService.count();
    }

    @GetMapping("/residents/search")
    public List<Resident> findResidentByTime(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalTime
            , @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureTime) {
        List<Resident> residentList = residentService.findAllByTime(arrivalTime, departureTime);
        return residentList;
    }


}
