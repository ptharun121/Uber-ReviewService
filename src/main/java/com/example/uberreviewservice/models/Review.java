package com.example.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends BaseModel {

	@Column(nullable = false)
	private String content;

	private Double rating;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Booking booking;

	@Override
	public String toString() {
		return "Review [id=" + id + ", content=" + content + ", rating=" + rating + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
