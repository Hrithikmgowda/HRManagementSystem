package com.example.controller;

import com.example.dto.PerformanceReviewDTO;
import com.example.service.PerformanceReviewService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance-reviews")
public class PerformanceReviewController {

    private final PerformanceReviewService performanceReviewService;

    public PerformanceReviewController(
            PerformanceReviewService performanceReviewService) {
        this.performanceReviewService = performanceReviewService;
    }

    @PostMapping
    public ResponseEntity<PerformanceReviewDTO> createPerformanceReview(
            @Valid @RequestBody PerformanceReviewDTO reviewDTO) {

        PerformanceReviewDTO savedReview =
                performanceReviewService.createPerformanceReview(reviewDTO);

        return new ResponseEntity<>(
                savedReview,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<PerformanceReviewDTO> getPerformanceReviewById(
            @PathVariable Long reviewId) {

        PerformanceReviewDTO review =
                performanceReviewService.getPerformanceReviewById(reviewId);

        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<PerformanceReviewDTO>> getAllPerformanceReviews() {

        List<PerformanceReviewDTO> reviews =
                performanceReviewService.getAllPerformanceReviews();

        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<PerformanceReviewDTO> updatePerformanceReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody PerformanceReviewDTO reviewDTO) {

        PerformanceReviewDTO updatedReview =
                performanceReviewService.updatePerformanceReview(
                        reviewId,
                        reviewDTO
                );

        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deletePerformanceReview(
            @PathVariable Long reviewId) {

        performanceReviewService.deletePerformanceReview(reviewId);

        return ResponseEntity.noContent().build();
    }
}