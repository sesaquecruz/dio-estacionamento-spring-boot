package com.parking.repository;

import com.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Parking, Long> {
    @Query("SELECT p FROM Parking p WHERE p.license = (:license) ORDER BY p.timestamp DESC")
    public List<Parking> findByLicense(@Param("license") String license);

    @Query("SELECT p FROM Parking p WHERE p.code = (:code)")
    public Parking findByCode(@Param("code") String code);

    @Query("UPDATE Parking p set p.checkout = (:checkout) WHERE p.code = (:code)")
    public void updateByCode(
            @Param("code") String code,
            @Param("checkout") Boolean checkout
            //bill
    );
}
