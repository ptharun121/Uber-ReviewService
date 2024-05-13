package com.example.uberreviewservice.services;

import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        Optional<Review> review;
        try {
            review = reviewRepository.findById(id);
            if (review.isEmpty()) {
                throw new EntityNotFoundException("Review not found for id " + id);
            }
        } catch (Exception e) {
            if (e.getClass() == EntityNotFoundException.class) {
                throw new FetchNotFoundException("Review not found for id ", id);
            }
            throw new FetchNotFoundException("try again later", e.getMessage());
        }
        return review;
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            reviewRepository.delete(review);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Review publishReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review updatedReview) {
        Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (updatedReview.getRating() != null) {
            review.setRating(updatedReview.getRating());
        }
        if (updatedReview.getContent() != null) {
            review.setContent(updatedReview.getContent());
        }
        return reviewRepository.save(review);
    }
}
