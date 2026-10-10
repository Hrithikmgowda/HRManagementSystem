package com.example.service;

import com.example.dto.LeaveTypeDTO;

import java.util.List;

public interface LeaveTypeService {

    LeaveTypeDTO createLeaveType(LeaveTypeDTO leaveTypeDTO);

    LeaveTypeDTO getLeaveTypeById(Long leaveTypeId);

    List<LeaveTypeDTO> getAllLeaveTypes();

    LeaveTypeDTO updateLeaveType(Long leaveTypeId, LeaveTypeDTO leaveTypeDTO);

    void deleteLeaveType(Long leaveTypeId);
}