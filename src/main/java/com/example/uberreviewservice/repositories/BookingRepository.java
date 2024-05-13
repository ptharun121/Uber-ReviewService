package com.example.uberreviewservice.repositories;

import com.example.uberreviewservice.models.Driver;
import com.example.uberreviewservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uberreviewservice.models.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByDriverId(Long driverId);

    List<Booking> findAllByDriverIn(List<Driver> drivers);

    /*@Query("SELECT r FROM Booking b inner join Review r on b.review = r where b.id = :bookingId")
    Review findReviewByBookingId(Long bookingId);*/
}
