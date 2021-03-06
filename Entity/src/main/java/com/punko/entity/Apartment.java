package com.punko.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * If you reference another object using non-primary key column, make the referenced object serializable.
 * and make that column with @NaturalId
 */
@Entity
@Table(name = "apartment")
public class Apartment
//        implements Serializable
{

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "apartmentSequence", sequenceName = "public.apartment_apartment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartmentSequence")
    @Column(name = "APARTMENT_ID")
    private Integer apartmentId;

    @Min(value = 1, message = "Apartment number should be more than 0")
    @Max(value = 1000, message = "Apartment number should be less than 1001")
    @NotNull
//    @NaturalId
    @Column(name = "APARTMENT_NUMBER", unique = true)
    private Integer apartmentNumber;

    @NotNull
    @Column(name = "APARTMENT_CLASS")
    private String apartmentClass;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            mappedBy = "apartment")
    List<Resident> residentList;

    public void addResidentToApartment(Resident resident) {
        if (residentList == null) {
            residentList = new ArrayList<>();
        }
        residentList.add(resident);
        resident.setApartment(this);
    }

    public Apartment() {
    }

    public Apartment(Integer apartmentNumber, String apartmentClass) {
        this.apartmentNumber = apartmentNumber;
        this.apartmentClass = apartmentClass;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getApartmentClass() {
        return apartmentClass;
    }

    public void setApartmentClass(String apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", apartmentNumber=" + apartmentNumber +
                ", apartmentClass='" + apartmentClass + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(apartmentId, apartment.apartmentId) && Objects.equals(apartmentNumber, apartment.apartmentNumber) && Objects.equals(apartmentClass, apartment.apartmentClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentId, apartmentNumber, apartmentClass);
    }
}