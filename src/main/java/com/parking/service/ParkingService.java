package com.parking.service;

import com.parking.exception.ParkingNotFoundException;
import com.parking.repository.ParkRepository;
import com.parking.exception.ParkingInvalidException;
import com.parking.model.Parking;
import com.parking.model.ParkingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {
    @Autowired
    private ParkRepository parkRepository;

    public Parking checkin(ParkingDTO parkingDTO) {
        Parking parking = new Parking();
        parking.setCode(UUID.randomUUID().toString().replace("-", ""));
        parking.setLicense(parkingDTO.getLicense());
        parking.setPrice(parkingDTO.getPrice());
        parking.setCheckout(false);
        parking.setEntryDate(LocalDateTime.now());

        try {
            parkRepository.save(parking);
        } catch (RuntimeException e) {
            throw new ParkingInvalidException();
        }

        return parking;
    }

    public List<Parking> findAll() {
        return parkRepository.findAll();
    }

    public List<Parking> findByLicense(String license) {
        List<Parking> parkings = parkRepository.findByLicense(license);

        if(parkings.isEmpty())
            throw new ParkingNotFoundException();

        return parkings;
    }

    public Parking findByCode(String code) {
        Parking parking = parkRepository.findByCode(code);

        if(parking == null)
            throw new ParkingNotFoundException();

        return parking;
    }

    public Parking checkout(String code) {
        Parking parking = findByCode(code);

        if(!parking.getCheckout()) {
            parking.setExitDate(LocalDateTime.now());
            parking.setCheckout(true);
            parking.setBill(ChronoUnit.MINUTES.between(parking.getEntryDate(), parking.getExitDate()) * parking.getPrice());
            parkRepository.save(parking);
        }

        return parking;
    }

    public Parking delete(String code) {
        Parking parking = findByCode(code);
        parkRepository.deleteById(parking.getId());

        return parking;
    }
}
