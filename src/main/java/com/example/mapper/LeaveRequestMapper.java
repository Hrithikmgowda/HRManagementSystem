package com.example.mapper;

import com.example.dto.LeaveRequestDTO;
import com.example.entity.LeaveRequest;

public class LeaveRequestMapper {

    public static LeaveRequestDTO toDTO(LeaveRequest leaveRequest) {

        if (leaveRequest == null) {
            return null;
        }

        Long employeeId = null;
        String employeeName = null;

        Long leaveTypeId = null;
        String leaveTypeName = null;

        if (leaveRequest.getEmployee() != null) {

            employeeId =
                    leaveRequest.getEmployee().getEmployeeId();

            employeeName =
                    leaveRequest.getEmployee().getFirstName()
                    + " "
                    + leaveRequest.getEmployee().getLastName();
        }

        if (leaveRequest.getLeaveType() != null) {

            leaveTypeId =
                    leaveRequest.getLeaveType().getLeaveTypeId();

            leaveTypeName =
                    leaveRequest.getLeaveType().getLeaveTypeName();
        }

        return new LeaveRequestDTO(
                leaveRequest.getLeaveRequestId(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getNumberOfDays(),
                leaveRequest.getReason(),
                leaveRequest.getStatus(),
                leaveRequest.getManagerComment(),
                employeeId,
                employeeName,
                leaveTypeId,
                leaveTypeName
        );
    }

    public static LeaveRequest toEntity(LeaveRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setLeaveRequestId(
                dto.getLeaveRequestId());

        leaveRequest.setStartDate(
                dto.getStartDate());

        leaveRequest.setEndDate(
                dto.getEndDate());

        /*
         * numberOfDays is NOT set here.
         * It is calculated in LeaveRequestServiceImpl
         * using startDate and endDate.
         */

        leaveRequest.setReason(
                dto.getReason());

        leaveRequest.setStatus(
                dto.getStatus());

        leaveRequest.setManagerComment(
                dto.getManagerComment());

        return leaveRequest;
    }
}

