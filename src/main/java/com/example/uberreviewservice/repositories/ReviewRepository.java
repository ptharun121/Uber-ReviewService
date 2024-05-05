package com.example.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.uberreviewservice.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
