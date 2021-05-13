package com.punko.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Apartments")
public class Apartment {

    @Column(name = "apartment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer apartmentId;

    @Column(name = "apartment_number")
//    @NotNull(message = "Apartment number is required field")
//    @Min(value = 1, message = "Apartment number should be more than 0")
//    @Max(value = 500, message = "Apartment number should be less than 501")
    private Integer apartmentNumber;

    @Column(name = "apartment_class")
    private String apartmentClass;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            mappedBy = "apartment")
    List<Resident> residentList;

//    public void addResidentToApartment(Resident resident) {
//        if (residentList == null) {
//            residentList = new ArrayList<>();
//        }
//        residentList.add(resident);
//        resident.setApartment(this);
//    }

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
}
