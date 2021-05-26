package com.punko;

import com.punko.entity.Resident;

import java.time.LocalDate;
import java.util.List;

public interface ResidentService {

    List<Resident> getAllResident();

    void saveResident(Resident resident);

    Resident getById(Integer id);

    void deleteResident(Integer id);

    List<Resident> findAllByTime(LocalDate arrivalTime, LocalDate departureTime);

    Long count();
}
