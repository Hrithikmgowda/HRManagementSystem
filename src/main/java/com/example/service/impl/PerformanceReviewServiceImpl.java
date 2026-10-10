package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.PerformanceReviewDTO;
import com.example.entity.Employee;
import com.example.entity.PerformanceReview;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.PerformanceReviewMapper;
import com.example.repository.EmployeeRepository;
import com.example.repository.PerformanceReviewRepository;
import com.example.service.PerformanceReviewService;

@Service
public class PerformanceReviewServiceImpl
        implements PerformanceReviewService {

    private final PerformanceReviewRepository performanceReviewRepository;
    private final EmployeeRepository employeeRepository;

    public PerformanceReviewServiceImpl(
            PerformanceReviewRepository performanceReviewRepository,
            EmployeeRepository employeeRepository) {

        this.performanceReviewRepository = performanceReviewRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public PerformanceReviewDTO createPerformanceReview(
            PerformanceReviewDTO reviewDTO) {

        Employee employee = employeeRepository
                .findById(reviewDTO.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with ID: "
                                + reviewDTO.getEmployeeId()));

        PerformanceReview review =
                PerformanceReviewMapper.toEntity(reviewDTO);

        review.setEmployee(employee);

        PerformanceReview savedReview =
                performanceReviewRepository.save(review);

        return PerformanceReviewMapper.toDTO(savedReview);
    }

    @Override
    public PerformanceReviewDTO getPerformanceReviewById(
            Long reviewId) {

        PerformanceReview review =
                performanceReviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Performance review not found with ID: "
                                + reviewId));

        return PerformanceReviewMapper.toDTO(review);
    }

    @Override
    public List<PerformanceReviewDTO> getAllPerformanceReviews() {

        return performanceReviewRepository.findAll()
                .stream()
                .map(PerformanceReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PerformanceReviewDTO updatePerformanceReview(
            Long reviewId,
            PerformanceReviewDTO reviewDTO) {

        PerformanceReview review =
                performanceReviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Performance review not found with ID: "
                                + reviewId));

        review.setReviewDate(
                reviewDTO.getReviewDate());

        review.setRating(
                reviewDTO.getRating());

        review.setComments(
                reviewDTO.getComments());

        review.setReviewPeriod(
                reviewDTO.getReviewPeriod());

        review.setReviewerName(
                reviewDTO.getReviewerName());

        if (reviewDTO.getEmployeeId() != null) {

            Employee employee = employeeRepository
                    .findById(reviewDTO.getEmployeeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Employee not found with ID: "
                                    + reviewDTO.getEmployeeId()));

            review.setEmployee(employee);
        }

        PerformanceReview updatedReview =
                performanceReviewRepository.save(review);

        return PerformanceReviewMapper.toDTO(updatedReview);
    }

    @Override
    public void deletePerformanceReview(Long reviewId) {

        PerformanceReview review =
                performanceReviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Performance review not found with ID: "
                                + reviewId));

        performanceReviewRepository.delete(review);
    }
}

