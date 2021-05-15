package com.punko.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApartmentIncorrectData> handleException(ApartmentException exception) {
        ApartmentIncorrectData incorrectData = new ApartmentIncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApartmentIncorrectData> handleException(IllegalArgumentException exception) {
        ApartmentIncorrectData incorrectData = new ApartmentIncorrectData();
        incorrectData.setInfo(exception.getLocalizedMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }
}
