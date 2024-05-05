package com.example.uberreviewservice.services;

import java.util.*;

import com.example.uberreviewservice.models.CustomDriver;
import com.example.uberreviewservice.models.Driver;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.BookingRepository;
import com.example.uberreviewservice.repositories.DriverRepository;
import com.example.uberreviewservice.repositories.ReviewRepository;

@Service
public class ReviewService implements CommandLineRunner {

	private ReviewRepository reviewRepository;
	
	private BookingRepository bookingRepository;
	
	private DriverRepository driverRepository;

	public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository,
			DriverRepository driverRepository) {
		this.reviewRepository = reviewRepository;
		this.bookingRepository = bookingRepository;
		this.driverRepository = driverRepository;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		/*Review review = Review.builder().content("Amazing Ride").rating(5.0)
				.build();
		
		Booking booking = Booking.builder().review(review).endTime(new Date()).build();
		this.bookingRepository.save(booking);
		
		//this.reviewRepository.save(review);
		
		List<Review> reviews = this.reviewRepository.findAll();
		System.out.println(reviews.get(0).getId());
		
		Optional<Booking> b = this.bookingRepository.findById(4L);
		if (b.isPresent()) {
			this.bookingRepository.deleteById(4L);
		}*/
		//this.reviewRepository.deleteAll();

		/*Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L, "AP03563737H");
		if (driver.isPresent()) {
			System.out.println(driver.get().getName());
			List<Booking> bookings = bookingRepository.findAllByDriverId(1L);
			System.out.println(bookings.size());
		}

		Optional<Object> d = driverRepository.rawfindByIdAndLicenseNumber(1L, "AP03563737H");
		if (d.isPresent()) {
			var drv = d.map(Driver.class::cast).get();
		}

		System.out.println(((Driver)d.get()).getName());

		Optional<CustomDriver> dcons = driverRepository.rawfindByIdAndLicenseNumberConstructor(1L, "AP03563737H");
		System.out.println(dcons);*/

		List<Long> driverIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));
		List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);

		//List<Booking> bookings1 = bookingRepository.findAllByDriverIn(drivers);

		System.out.println("###################################################");

		for (Driver driver : drivers) {
			final List<Booking> bookings = driver.getBookings();
			System.out.println(bookings.get(0).getId());
			//bookings.forEach(booking -> System.out.println(booking.getId()));
		}
	}

}
