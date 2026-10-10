package com.example.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.LeaveRequestDTO;
import com.example.entity.Employee;
import com.example.entity.LeaveBalance;
import com.example.entity.LeaveRequest;
import com.example.entity.LeaveType;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.LeaveRequestMapper;
import com.example.repository.EmployeeRepository;
import com.example.repository.LeaveBalanceRepository;
import com.example.repository.LeaveRequestRepository;
import com.example.repository.LeaveTypeRepository;
import com.example.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository,
            LeaveTypeRepository leaveTypeRepository,
            LeaveBalanceRepository leaveBalanceRepository) {

        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    @Override
    @Transactional
    public LeaveRequestDTO createLeaveRequest(
            LeaveRequestDTO leaveRequestDTO) {

        Employee employee = employeeRepository
                .findById(leaveRequestDTO.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with ID: "
                                + leaveRequestDTO.getEmployeeId()));

        LeaveType leaveType = leaveTypeRepository
                .findById(leaveRequestDTO.getLeaveTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave type not found with ID: "
                                + leaveRequestDTO.getLeaveTypeId()));

        if (leaveRequestDTO.getStartDate()
                .isAfter(leaveRequestDTO.getEndDate())) {

            throw new IllegalArgumentException(
                    "Start date cannot be after end date");
        }

        long numberOfDays = ChronoUnit.DAYS.between(
                leaveRequestDTO.getStartDate(),
                leaveRequestDTO.getEndDate()) + 1;

        if (numberOfDays > leaveType.getMaximumDays()) {

            throw new IllegalArgumentException(
                    "Leave request exceeds maximum allowed days of "
                    + leaveType.getMaximumDays());
        }

        LeaveRequest leaveRequest =
                LeaveRequestMapper.toEntity(leaveRequestDTO);

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setNumberOfDays((int) numberOfDays);
        leaveRequest.setStatus("PENDING");

        LeaveRequest savedLeaveRequest =
                leaveRequestRepository.save(leaveRequest);

        return LeaveRequestMapper.toDTO(savedLeaveRequest);
    }

    @Override
    public LeaveRequestDTO getLeaveRequestById(
            Long leaveRequestId) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave request not found with ID: "
                                + leaveRequestId));

        return LeaveRequestMapper.toDTO(leaveRequest);
    }

    @Override
    public List<LeaveRequestDTO> getAllLeaveRequests() {

        return leaveRequestRepository.findAll()
                .stream()
                .map(LeaveRequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LeaveRequestDTO updateLeaveRequest(
            Long leaveRequestId,
            LeaveRequestDTO leaveRequestDTO) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave request not found with ID: "
                                + leaveRequestId));

        if ("APPROVED".equalsIgnoreCase(
                leaveRequest.getStatus())) {

            throw new IllegalArgumentException(
                    "Approved leave request cannot be updated");
        }

        if (leaveRequestDTO.getStartDate()
                .isAfter(leaveRequestDTO.getEndDate())) {

            throw new IllegalArgumentException(
                    "Start date cannot be after end date");
        }

        LeaveType leaveType = leaveTypeRepository
                .findById(leaveRequestDTO.getLeaveTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave type not found with ID: "
                                + leaveRequestDTO.getLeaveTypeId()));

        long numberOfDays = ChronoUnit.DAYS.between(
                leaveRequestDTO.getStartDate(),
                leaveRequestDTO.getEndDate()) + 1;

        if (numberOfDays > leaveType.getMaximumDays()) {

            throw new IllegalArgumentException(
                    "Leave request exceeds maximum allowed days of "
                    + leaveType.getMaximumDays());
        }

        leaveRequest.setStartDate(
                leaveRequestDTO.getStartDate());

        leaveRequest.setEndDate(
                leaveRequestDTO.getEndDate());

        leaveRequest.setNumberOfDays(
                (int) numberOfDays);

        leaveRequest.setReason(
                leaveRequestDTO.getReason());

        leaveRequest.setManagerComment(
                leaveRequestDTO.getManagerComment());

        if (leaveRequestDTO.getEmployeeId() != null) {

            Employee employee = employeeRepository
                    .findById(leaveRequestDTO.getEmployeeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Employee not found with ID: "
                                    + leaveRequestDTO.getEmployeeId()));

            leaveRequest.setEmployee(employee);
        }

        leaveRequest.setLeaveType(leaveType);

        LeaveRequest updatedLeaveRequest =
                leaveRequestRepository.save(leaveRequest);

        return LeaveRequestMapper.toDTO(updatedLeaveRequest);
    }

    @Override
    @Transactional
    public void deleteLeaveRequest(Long leaveRequestId) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave request not found with ID: "
                                + leaveRequestId));

        leaveRequestRepository.delete(leaveRequest);
    }

    @Override
    @Transactional
    public LeaveRequestDTO approveLeaveRequest(
            Long leaveRequestId,
            String managerComment) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave request not found with ID: "
                                + leaveRequestId));

        if (!"PENDING".equalsIgnoreCase(
                leaveRequest.getStatus())) {

            throw new IllegalArgumentException(
                    "Only pending leave requests can be approved");
        }

        Employee employee = leaveRequest.getEmployee();
        LeaveType leaveType = leaveRequest.getLeaveType();

        LeaveBalance leaveBalance =
                leaveBalanceRepository
                        .findByEmployeeAndLeaveType(
                                employee,
                                leaveType)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Leave balance not found for employee ID: "
                                        + employee.getEmployeeId()
                                        + " and leave type ID: "
                                        + leaveType.getLeaveTypeId()));

        int requestedDays =
                leaveRequest.getNumberOfDays();

        if (leaveBalance.getRemainingDays() < requestedDays) {

            throw new IllegalArgumentException(
                    "Insufficient leave balance");
        }

        leaveBalance.setUsedDays(
                leaveBalance.getUsedDays() + requestedDays);

        leaveBalance.setRemainingDays(
                leaveBalance.getRemainingDays() - requestedDays);

        leaveRequest.setStatus("APPROVED");
        leaveRequest.setManagerComment(managerComment);

        leaveBalanceRepository.save(leaveBalance);

        LeaveRequest approvedLeaveRequest =
                leaveRequestRepository.save(leaveRequest);

        return LeaveRequestMapper.toDTO(
                approvedLeaveRequest);
    }

    @Override
    @Transactional
    public LeaveRequestDTO rejectLeaveRequest(
            Long leaveRequestId,
            String managerComment) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave request not found with ID: "
                                + leaveRequestId));

        if (!"PENDING".equalsIgnoreCase(
                leaveRequest.getStatus())) {

            throw new IllegalArgumentException(
                    "Only pending leave requests can be rejected");
        }

        leaveRequest.setStatus("REJECTED");
        leaveRequest.setManagerComment(managerComment);

        LeaveRequest rejectedLeaveRequest =
                leaveRequestRepository.save(leaveRequest);

        return LeaveRequestMapper.toDTO(
                rejectedLeaveRequest);
    }
}
