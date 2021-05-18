package com.punko.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "resident")
public class Resident {

    @Column(name = "RESIDENT_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer residentId;

    @NotNull(message = "First name is a required field")
    @Size(min = 2, max = 20, message = "First name should be min 2, max 20 symbols")
    @Column(name = "FIRSTNAME")
    private String firstName;

    @NotNull(message = "Last name is a required field")
    @Size(min = 2, max = 20, message = "Last name should be min 2, max 20 symbols")
    @Column(name = "LASTNAME")
    private String lastName;

    @NotNull(message = "Email name is a required field")
    @Size(min = 2, max = 50, message = "Email name should be min 2, max 50 symbols")
    @Email(message = "use correct email")
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotNull(message = "arrival time is a required field")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ARRIVAL_TIME")
    private LocalDate arrivalTime;

    @NotNull(message = "departure time is a required field")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DEPARTURE_TIME")
    private LocalDate departureTime;

//    @Min(value = 1, message = "Apartment number is a required field")
//    @Column(name = "APARTMENT_NUMBER")
//    private Integer apartmentNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "APARTMENT_ID", referencedColumnName = "APARTMENT_ID")
    @NotNull
//    private Integer apartmentNumber;
    Apartment apartment;

    public Resident() {
    }

    public Resident(String firstName, String lastName, String email, LocalDate arrivalTime,
                    LocalDate departureTime
//                    Integer apartmentNumber
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
//        this.apartmentNumber = apartmentNumber;
    }

    public Integer getResidentId() {
        return residentId;
    }

    public void setResidentId(Integer residentId) {
        this.residentId = residentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

//    public Integer getApartmentNumber() {
//        return apartmentNumber;
//    }
//
//    public void setApartmentNumber(Integer apartmentNumber) {
//        this.apartmentNumber = apartmentNumber;
//    }

//    @Override
//    public String toString() {
//        return "Resident{" +
//                "residentId=" + residentId +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", arrivalTime=" + arrivalTime +
//                ", departureTime=" + departureTime +
//                ", apartment=" + apartment +
//                '}';
//    }


    @Override
    public String toString() {
        return "Resident{" +
                "residentId=" + residentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return Objects.equals(residentId, resident.residentId) && Objects.equals(firstName, resident.firstName) && Objects.equals(lastName, resident.lastName) && Objects.equals(email, resident.email) && Objects.equals(arrivalTime, resident.arrivalTime) && Objects.equals(departureTime, resident.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(residentId, firstName, lastName, email, arrivalTime, departureTime);
    }
}