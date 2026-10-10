package com.example.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PerformanceReviewDTO {

    private Long reviewId;

    @NotNull(message = "Review date is required")
    private LocalDate reviewDate;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    private Integer rating;

    @NotBlank(message = "Comments are required")
    private String comments;

    @NotBlank(message = "Review period is required")
    private String reviewPeriod;

    @NotBlank(message = "Reviewer name is required")
    private String reviewerName;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    private String employeeName;

    public PerformanceReviewDTO() {
    }

    public PerformanceReviewDTO(Long reviewId,
                                LocalDate reviewDate,
                                Integer rating,
                                String comments,
                                String reviewPeriod,
                                String reviewerName,
                                Long employeeId,
                                String employeeName) {

        this.reviewId = reviewId;
        this.reviewDate = reviewDate;
        this.rating = rating;
        this.comments = comments;
        this.reviewPeriod = reviewPeriod;
        this.reviewerName = reviewerName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReviewPeriod() {
        return reviewPeriod;
    }

    public void setReviewPeriod(String reviewPeriod) {
        this.reviewPeriod = reviewPeriod;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}