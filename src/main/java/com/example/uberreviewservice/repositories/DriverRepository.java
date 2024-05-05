package com.example.uberreviewservice.repositories;

import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.CustomDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    //@Query(nativeQuery = true, value = "SELECT * FROM driver WHERE id = :id AND license_number = :licenseNumber")
    //Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query("select d from Driver d where d.id = :id AND d.licenseNumber = :licenseNumber")
    Optional<Object> rawfindByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query("SELECT new com.example.uberreviewservice.models.CustomDriver(id, name) From Driver d where d.id = :id AND d.licenseNumber = :licenseNumber")
    Optional<CustomDriver> rawfindByIdAndLicenseNumberConstructor(Long id, String licenseNumber);

    List<Driver> findAllByIdIn(List<Long> ids);
}
