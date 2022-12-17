package com.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ParkingInvalidException extends RuntimeException {
    public ParkingInvalidException() {
        super("Parking invalid");
    }
    public ParkingInvalidException(String message) {
        super(message);
    }
}
