package com.example.uberreviewservice.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseModel {

	@ManyToOne(fetch = FetchType.LAZY)
	private Driver driver;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Passenger passenger;

	@Enumerated(value = EnumType.STRING)
	private BookingStatus bookingStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	private Long totalDistance;
}
