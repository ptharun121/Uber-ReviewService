package com.example.uberreviewservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
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

	@Override
	public String toString() {
		return "Review [id=" + id + ", content=" + content + ", rating=" + rating + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
