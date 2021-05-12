package com.punko.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Table(name = "Residents")
public class Resident {

    @Column(name = "resident_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer residentId;

    @Column(name = "resident_firstname")
    @Size(min = 2, max = 20, message = "first name should be min 2 and max 20 symbols")
    private String residentFirstname;

    @Column(name = "resident_lastname")

    @Size(min = 2, max = 20, message = "last name should be min 2 and max 20 symbols")
    private String residentLastname;

    @Column(name = "resident_email")
    @NotBlank(message = "Email is required field")
    @Size(min = 7, max = 20, message = "email should be min 7 and max 30 symbols")
    private String residentEmail;

    @Column(name = "arrival_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalTime;

    @Column(name = "departure_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "apartment_id")
    Apartment apartment;

    public Resident() {
    }

    public Resident(String residentFirstname, String residentLastname, String residentEmail, LocalDate arrivalTime, LocalDate departureTime) {
        this.residentFirstname = residentFirstname;
        this.residentLastname = residentLastname;
        this.residentEmail = residentEmail;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Integer getResidentId() {
        return residentId;
    }

    public void setResidentId(Integer residentId) {
        this.residentId = residentId;
    }

    public String getResidentFirstname() {
        return residentFirstname;
    }

    public void setResidentFirstname(String residentFirstname) {
        this.residentFirstname = residentFirstname;
    }

    public String getResidentLastname() {
        return residentLastname;
    }

    public void setResidentLastname(String residentLastname) {
        this.residentLastname = residentLastname;
    }

    public String getResidentEmail() {
        return residentEmail;
    }

    public void setResidentEmail(String residentEmail) {
        this.residentEmail = residentEmail;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentId=" + residentId +
                ", residentFirstname='" + residentFirstname + '\'' +
                ", residentLastname='" + residentLastname + '\'' +
                ", residentEmail='" + residentEmail + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", apartment=" + apartment +
                '}';
    }
}
