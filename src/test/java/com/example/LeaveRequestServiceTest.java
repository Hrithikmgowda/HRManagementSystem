package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.entity.LeaveRequest;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EmployeeRepository;
import com.example.repository.LeaveBalanceRepository;
import com.example.repository.LeaveRequestRepository;
import com.example.repository.LeaveTypeRepository;
import com.example.service.impl.LeaveRequestServiceImpl;

class LeaveRequestServiceTest {

    private LeaveRequestRepository leaveRequestRepository;
    private EmployeeRepository employeeRepository;
    private LeaveTypeRepository leaveTypeRepository;
    private LeaveBalanceRepository leaveBalanceRepository;

    private LeaveRequestServiceImpl leaveRequestService;

    @BeforeEach
    void setUp() {
        leaveRequestRepository = mock(LeaveRequestRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        leaveTypeRepository = mock(LeaveTypeRepository.class);
        leaveBalanceRepository = mock(LeaveBalanceRepository.class);

        leaveRequestService = new LeaveRequestServiceImpl(
                leaveRequestRepository,
                employeeRepository,
                leaveTypeRepository,
                leaveBalanceRepository);
    }

    @Test
    void shouldRejectApprovalWhenLeaveRequestIsAlreadyApproved() {

        Long leaveRequestId = 100L;

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setLeaveRequestId(leaveRequestId);
        leaveRequest.setStatus("APPROVED");

        when(leaveRequestRepository.findById(leaveRequestId))
                .thenReturn(Optional.of(leaveRequest));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> leaveRequestService.approveLeaveRequest(
                        leaveRequestId, "Approved again"));

        assertEquals(
                "Only pending leave requests can be approved",
                exception.getMessage());

        verify(leaveBalanceRepository, never())
                .save(org.mockito.ArgumentMatchers.any());

        verify(leaveRequestRepository, never())
                .save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void shouldThrowExceptionWhenLeaveRequestDoesNotExist() {

        Long missingId = 999999L;

        when(leaveRequestRepository.findById(missingId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> leaveRequestService.approveLeaveRequest(
                        missingId, "Test approval"));
    }
}

