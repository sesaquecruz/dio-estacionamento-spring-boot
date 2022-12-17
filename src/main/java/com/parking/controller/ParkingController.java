package com.parking.controller;

import com.parking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import com.parking.model.Parking;
import com.parking.model.ParkingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping
    @Operation(tags = {"Check in"})
    public ResponseEntity<Parking> checkin(@RequestBody ParkingDTO parkingDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.checkin(parkingDTO));
    }

    @GetMapping
    @Operation(tags = {"Get all"})
    public ResponseEntity<List<Parking>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.findAll());
    }

    @GetMapping("/license/{license}")
    @Operation(tags = {"Get by license"})
    public ResponseEntity<List<Parking>> getByLicense(@PathVariable String license) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.findByLicense(license));
    }

    @GetMapping("/code/{code}")
    @Operation(tags = {"Get  by code"})
    public ResponseEntity<Parking> getByCode(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.findByCode(code));
    }

    @PutMapping("/code/{code}")
    @Operation(tags = {"Checkout"})
    public ResponseEntity<Parking> checkout(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.checkout(code));
    }

    @DeleteMapping("/code/{code}")
    @Operation(tags = {"Delete by code"})
    public ResponseEntity<Parking> delete(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.delete(code));
    }
}
