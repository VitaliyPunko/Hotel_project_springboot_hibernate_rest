package com.punko;


import com.punko.entity.Apartment;

import java.util.List;

/**
 * ApartmentDao DAO Interface.
 */
public interface ApartmentDAO {

    /**
     * Get all apartments with average time between
     * arrival time and departure time.
     *
     * @return apartment list.
     */
    List<Apartment> getAllApartment();

    /**
     * Get apartment by id
     *
     * @param id apartment id
     * @return apartment.
     */
    Apartment getById(Integer id);

    /**
     * Create or update new apartment
     *
     * @param apartment new Apartment
     */
    void saveApartment(Apartment apartment);

    /**
     * Delete apartment
     *
     * @param id apartment id
     * @return count of deleted apartments.
     */
    void delete(Integer id);

    /**
     * Count of all apartments
     *
     * @return count of apartments.
     */
    Long count();
}
