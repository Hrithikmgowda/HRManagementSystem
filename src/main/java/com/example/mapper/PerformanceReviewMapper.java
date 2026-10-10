package com.example.mapper;

import com.example.dto.PerformanceReviewDTO;
import com.example.entity.PerformanceReview;

public class PerformanceReviewMapper {

    public static PerformanceReviewDTO toDTO(PerformanceReview review) {

        if (review == null) {
            return null;
        }

        Long employeeId = null;
        String employeeName = null;

        if (review.getEmployee() != null) {
            employeeId = review.getEmployee().getEmployeeId();
            employeeName = review.getEmployee().getFirstName()
                    + " " + review.getEmployee().getLastName();
        }

        return new PerformanceReviewDTO(
                review.getReviewId(),
                review.getReviewDate(),
                review.getRating(),
                review.getComments(),
                review.getReviewPeriod(),
                review.getReviewerName(),
                employeeId,
                employeeName
        );
    }

    public static PerformanceReview toEntity(PerformanceReviewDTO dto) {

        if (dto == null) {
            return null;
        }

        PerformanceReview review = new PerformanceReview();

        review.setReviewId(dto.getReviewId());
        review.setReviewDate(dto.getReviewDate());
        review.setRating(dto.getRating());
        review.setComments(dto.getComments());
        review.setReviewPeriod(dto.getReviewPeriod());
        review.setReviewerName(dto.getReviewerName());

        return review;
    }
}
