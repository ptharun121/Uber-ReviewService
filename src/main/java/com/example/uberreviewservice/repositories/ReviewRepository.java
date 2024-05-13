package com.example.uberreviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.uberreviewservice.models.Review;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Integer countAllByRatingIsLessThanEqual(Integer rating);

    List<Review> findAllByRatingIsLessThanEqual(Integer rating);

    List<Review> findAllByCreatedAtBefore(Date createdAt);

}
