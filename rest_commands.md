## Available REST endpoints

### Apartments

#### Find all apartments

```
curl --request GET 'http://localhost:8080/apartments' | json_pp
```

#### Find apartment by id

```
curl --request GET 'http://localhost:8080/apartments/1' | json_pp
```

#### Get apartment by id that doesn't exist

```
curl --request GET 'http://localhost:8080/apartments/1000000' | json_pp
```

#### Get count of apartment

```
curl --request GET 'http://localhost:8080/apartments/count' | json_pp
```

#### Create new apartment

```
curl --request POST 'http://localhost:8080/apartments/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "apartmentNumber":"109",
    "apartmentClass":"MEDIUM"
}'
```

#### Edit apartment

```
curl --request PUT 'http://localhost:8080/apartments/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "apartmentId":3,
    "apartmentNumber":555,
    "apartmentClass":"LUXURIOUS"
}'
```

#### Edit apartment with already exist apartment number

```
curl --request PUT 'http://localhost:8080/apartments/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "apartmentId":3,
    "apartmentNumber":102,
    "apartmentClass":"LUXURIOUS"
}'
```

#### Delete apartment by id

```
curl --request DELETE 'http://localhost:8080/apartments/6'
```

#### Delete apartment with wrong id

```
curl --request DELETE 'http://localhost:8080/apartments/10000000'
```

### Residents

#### Find all residents

```
curl --request GET 'http://localhost:8080/residents' | json_pp
```

#### Find resident by id

```
curl --request GET 'http://localhost:8080/residents/1' | json_pp
```

#### Find resident by id that doesn't exist

```
curl --request GET 'http://localhost:8080/residents/10000000' | json_pp
```

#### Find residents from arrival time to departure time

```
curl --request GET 'http://localhost:8080/residents/search?arrivalTime=2021-03-01&departureTime=2021-07-02'
```

#### Create new resident

```
curl --request POST 'http://localhost:8080/residents/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
      "firstName": "Vitaliy",
      "lastName": "Punko",
      "email": "test@test.com",
      "arrivalTime": [2021, 3, 13],
      "departureTime": [2021, 3, 23],
      "apartment": {
        "apartmentId": 1,
        "apartmentNumber": 101,
        "apartmentClass": "LUXURIOUS"
    }
}'
```

#### Create resident with the same Email

```
curl --request POST 'http://localhost:8080/residents/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
      "firstName": "Vitaliy",
      "lastName": "Punko",
      "email": "denbrown@test.com",
      "arrivalTime": [2021, 3, 13],
      "departureTime": [2021, 3, 23],
      "apartment": {
        "apartmentId": 1,
        "apartmentNumber": 101,
        "apartmentClass": "LUXURIOUS"
    }
}'
```

#### Create resident with doesn't exist apartment

```
curl --request POST 'http://localhost:8080/residents/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
      "firstName": "Vitaliy",
      "lastName": "Punko",
      "email": "testAnotherEmail@test.com",
      "arrivalTime": [2021, 3, 13],
      "departureTime": [2021, 3, 23],
      "apartment": {
        "apartmentId": 5,
        "apartmentNumber": 99,
        "apartmentClass": "LUXURIOUS"
    }
}'
```

#### Create resident when arrival time after departure time

```
curl --request POST 'http://localhost:8080/residents/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
      "firstName": "Vitaliy",
      "lastName": "Punko",
      "email": "testAnotherEmail@test.com",
      "arrivalTime": [2021, 3, 13],
      "departureTime": [2000, 3, 23],
      "apartment": {
        "apartmentId": 1,
        "apartmentNumber": 101,
        "apartmentClass": "LUXURIOUS"
    }
}'
```

#### Edit resident

```
curl --request PUT 'http://localhost:8080/residents/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
         "residentId": 2,
        "firstName": "Margaret",
        "lastName": "Mitchell",
        "email": "margaret@test.com",
        "arrivalTime": [2020, 10, 26],
        "departureTime": [2021, 4, 10],
        "apartment": {
            "apartmentId": 2,
            "apartmentNumber": 102,
            "apartmentClass": "CHEAP"
        }
}'
```

#### Edit resident when arrival time after departure time

```
curl --request PUT 'http://localhost:8080/residents/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
         "residentId": 2,
        "firstName": "Margaret",
        "lastName": "Mitchell",
        "email": "margaret@test.com",
        "arrivalTime": [2020, 10, 26],
        "departureTime": [2000, 4, 10],
        "apartment": {
            "apartmentId": 2,
            "apartmentNumber": 102,
            "apartmentClass": "CHEAP"
        }
}'
```

#### Delete resident by id

```
curl --request DELETE 'http://localhost:8080/residents/7'
```

#### Delete resident with wrong id

```
curl --request DELETE 'http://localhost:8080/residents/700000000'
```