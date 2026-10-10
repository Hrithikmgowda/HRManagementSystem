package com.example.mapper;

import com.example.dto.LeaveBalanceDTO;
import com.example.entity.LeaveBalance;

public class LeaveBalanceMapper {

    public static LeaveBalanceDTO toDTO(LeaveBalance leaveBalance) {

        if (leaveBalance == null) {
            return null;
        }

        Long employeeId = null;
        String employeeName = null;
        Long leaveTypeId = null;
        String leaveTypeName = null;

        if (leaveBalance.getEmployee() != null) {
            employeeId = leaveBalance.getEmployee().getEmployeeId();
            employeeName = leaveBalance.getEmployee().getFirstName()
                    + " " + leaveBalance.getEmployee().getLastName();
        }

        if (leaveBalance.getLeaveType() != null) {
            leaveTypeId = leaveBalance.getLeaveType().getLeaveTypeId();
            leaveTypeName = leaveBalance.getLeaveType().getLeaveTypeName();
        }

        return new LeaveBalanceDTO(
                leaveBalance.getBalanceId(),
                leaveBalance.getTotalDays(),
                leaveBalance.getUsedDays(),
                leaveBalance.getRemainingDays(),
                employeeId,
                employeeName,
                leaveTypeId,
                leaveTypeName
        );
    }

    public static LeaveBalance toEntity(LeaveBalanceDTO dto) {

        if (dto == null) {
            return null;
        }

        LeaveBalance leaveBalance = new LeaveBalance();

        leaveBalance.setBalanceId(dto.getBalanceId());
        leaveBalance.setTotalDays(dto.getTotalDays());
        leaveBalance.setUsedDays(dto.getUsedDays());
        leaveBalance.setRemainingDays(dto.getRemainingDays());

        return leaveBalance;
    }
}