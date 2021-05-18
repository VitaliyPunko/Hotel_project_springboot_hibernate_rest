package com.punko;

import com.punko.entity.Apartment;
import com.punko.entity.Resident;

import java.time.LocalDate;
import java.util.List;

/**
 * ResidentDao DAO Interface.
 */
public interface ResidentDAO {

    /**
     * Get all residents
     *
     * @return resident list.
     */
    List<Resident> getAllResident();

    /**
     * Create or update new resident
     *
     * @param resident new Resident.
     */
    void saveResident(Resident resident);

    /**
     * Get resident by id
     *
     * @param id resident id
     * @return resident.
     */
    Resident getById(Integer id);


    /**
     * Delete resident
     *
     * @param id resident id
     * @return count of deleted residents.
     */
    void deleteResident(Integer Id);

    /**
     * Get all exist apartment's number
     *
     * @return apartment list.
     */
    List<Apartment> getAllApartmentNumber();

    Integer count();

    /**
     * Get residents from arrivalTime to departureTime
     *
     * @param arrivalTime   resident arrivalTime
     * @param departureTime resident departureTime
     * @return resident list.
     */
    List<Resident> findAllByTime(LocalDate arrivalTime, LocalDate departureTime);
}
