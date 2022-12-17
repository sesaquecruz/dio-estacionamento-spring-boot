package com.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends RuntimeException {
    public ParkingNotFoundException() {
        super("Parking not found");
    }
    public ParkingNotFoundException(String message) {
        super(message);
    }
}
