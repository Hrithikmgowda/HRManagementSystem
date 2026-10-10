package com.example.service;

import com.example.dto.PerformanceReviewDTO;

import java.util.List;

public interface PerformanceReviewService {

    PerformanceReviewDTO createPerformanceReview(
            PerformanceReviewDTO reviewDTO);

    PerformanceReviewDTO getPerformanceReviewById(Long reviewId);

    List<PerformanceReviewDTO> getAllPerformanceReviews();

    PerformanceReviewDTO updatePerformanceReview(
            Long reviewId,
            PerformanceReviewDTO reviewDTO);

    void deletePerformanceReview(Long reviewId);
}