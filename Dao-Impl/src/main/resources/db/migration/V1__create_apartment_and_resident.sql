CREATE TABLE Apartment
(
    apartment_id     INT         NOT NULL AUTO_INCREMENT,
    apartment_number INT         NOT NULL UNIQUE,
    apartment_class  VARCHAR(30) NOT NULL,
    CONSTRAINT APARTMENT_PK PRIMARY KEY (apartment_id)
);

/**
  use ON DELETE SET NULL for FK.
  when delete apartment, apartment_id in Resident table become NULL
 */
CREATE TABLE Resident
(
    resident_id        INT         NOT NULL AUTO_INCREMENT,
    resident_firstname VARCHAR(30) NOT NULL,
    resident_lastname  VARCHAR(30) NOT NULL,
    resident_email     VARCHAR(30) NOT NULL UNIQUE,
    arrival_time       DATE        NOT NULL,
    departure_time     DATE        NOT NULL,
    apartment_id       INT,
    CONSTRAINT RESIDENT_PK PRIMARY KEY (resident_id),
    CONSTRAINT RESIDENT_APARTMENT_FK FOREIGN KEY (apartment_id) REFERENCES Apartment (apartment_id)
        ON DELETE SET NULL
);