package com.example.uberreviewservice.adapters;

import com.example.uberreviewservice.dtos.CreateReviewDto;
import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConvertReviewDtoToReviewAdapterImpl implements ConvertReviewDtoToReviewAdapter {

    private BookingRepository bookingRepository;

    public ConvertReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review coonvertDto(CreateReviewDto createReviewDto) {
        Optional<Booking> booking = bookingRepository.findById(createReviewDto.getBookingId());
        return booking.map(value -> Review.builder()
                .rating(createReviewDto.getRating())
                .content(createReviewDto.getContent())
                .booking(value).build()).orElse(null);
    }
}
