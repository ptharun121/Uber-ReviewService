package com.example.uberreviewservice.controllers;

import com.example.uberreviewservice.adapters.ConvertReviewDtoToReviewAdapter;
import com.example.uberreviewservice.adapters.ConvertReviewDtoToReviewAdapterImpl;
import com.example.uberreviewservice.dtos.CreateReviewDto;
import com.example.uberreviewservice.dtos.ReviewResponseDto;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ConvertReviewDtoToReviewAdapter convertReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService,
                            ConvertReviewDtoToReviewAdapter convertReviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.convertReviewDtoToReviewAdapter = convertReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> PublishReview(@RequestBody CreateReviewDto request) {
        Review inComingReview = this.convertReviewDtoToReviewAdapter.coonvertDto(request);
        if (inComingReview == null) {
            return new ResponseEntity<>("Invalid Argument", HttpStatus.BAD_REQUEST);
        }
        Review review = reviewService.publishReview(inComingReview);
        ReviewResponseDto response = ReviewResponseDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .bookingId(review.getBooking().getId())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long reviewId) {
        try {
            Optional<Review> review = reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            boolean isDeleted = reviewService.deleteReviewById(reviewId);
            if (!isDeleted) {
                return new ResponseEntity<>("unable delete", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("review deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request) {
        try {
            Review review = reviewService.updateReview(reviewId, request);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
