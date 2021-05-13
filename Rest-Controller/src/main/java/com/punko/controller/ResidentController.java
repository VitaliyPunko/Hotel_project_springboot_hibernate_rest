package com.punko.controller;

import com.punko.ResidentService;
import com.punko.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
