
# Hotel_project_springboot_hibernate_rest

This is simple 'Hotel-Resident' rest application, where you can use all CRUD operations. There are Apartment (one) and
Resident (many) entities in this application. Also, I added some simple features like my own annotation (for checking
Arrival time is before Departure time in Resident class)
and unit, mock and integration tests. 
I used mySQL DB. Than I added liquibase. And did migration from mysql to postgres
I want to study Docker and realize this application in it.

## Requirements

* JDK 11
* Apache Maven

## Build application:

```
mvn clean install
```

## Start application

### Start Rest application

To start Rest server:

```
java -jar ./Rest-Controller/target/Rest-Controller-0.0.1-SNAPSHOT.jar
```

Server up on [http://localhost:8080/apartments](http://localhost:8080/apartments).

### Available REST endpoints you can look [here](rest_commands.md)

### Also, you can use Postman. Look [here](postman.md)

### Data base configuration [here](SQL_scripts)


