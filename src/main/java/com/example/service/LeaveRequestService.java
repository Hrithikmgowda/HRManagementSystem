package com.example.service;

import com.example.dto.LeaveRequestDTO;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequestDTO createLeaveRequest(LeaveRequestDTO leaveRequestDTO);

    LeaveRequestDTO getLeaveRequestById(Long leaveRequestId);

    List<LeaveRequestDTO> getAllLeaveRequests();

    LeaveRequestDTO updateLeaveRequest(Long leaveRequestId,
                                       LeaveRequestDTO leaveRequestDTO);

    void deleteLeaveRequest(Long leaveRequestId);

    LeaveRequestDTO approveLeaveRequest(Long leaveRequestId,
                                        String managerComment);

    LeaveRequestDTO rejectLeaveRequest(Long leaveRequestId,
                                       String managerComment);
}