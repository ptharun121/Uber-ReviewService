package com.example.uberreviewservice.adapters;

import com.example.uberreviewservice.dtos.CreateReviewDto;
import com.example.uberreviewservice.models.Review;

public interface ConvertReviewDtoToReviewAdapter {

    public Review coonvertDto(CreateReviewDto createReviewDto);
}
