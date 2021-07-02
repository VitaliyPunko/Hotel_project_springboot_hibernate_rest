INSERT INTO Apartment (apartment_id, apartment_number, apartment_class)
VALUES (1, 101, 'LUXURY');
INSERT INTO Apartment (apartment_id, apartment_number, apartment_class)
VALUES (2, 102, 'CHEAP');
INSERT INTO Apartment (apartment_id, apartment_number, apartment_class)
VALUES (3, 105, 'MEDIUM');

Insert into Resident (resident_id, resident_firstname, resident_lastname, resident_email, arrival_time, departure_time,
                      apartment_id)
values (1, 'Stephen', 'King', 'stephenking@test.com', '2021-03-13', '2021-04-10', 1);
Insert into Resident (resident_id, resident_firstname, resident_lastname, resident_email, arrival_time, departure_time,
                      apartment_id)
values (2, 'Margaret', 'Mitchell', 'margaretmitchell@test.com', '2020-10-26', '2021-04-10', 2);
Insert into Resident (resident_id, resident_firstname, resident_lastname, resident_email, arrival_time, departure_time,
                      apartment_id)
values (3, 'Den', 'Brown', 'denbrown@test.com', '2021-02-13', '2021-05-01', 1);
Insert into Resident (resident_id, resident_firstname, resident_lastname, resident_email, arrival_time, departure_time,
                      apartment_id)
values (4, 'Erih', 'Remark', 'remark@test.com', '2021-04-13', '2021-06-01', 3);

Insert into Resident (resident_id, resident_firstname, resident_lastname, resident_email, arrival_time, departure_time,
                      apartment_id)
values (5, 'Erih', 'Remark', 'remark2@test.com', '2021-04-13', '2021-06-01', 2);