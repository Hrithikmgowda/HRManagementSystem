package com.example.mapper;

import com.example.dto.LeaveTypeDTO;
import com.example.entity.LeaveType;

public class LeaveTypeMapper {

    public static LeaveTypeDTO toDTO(LeaveType leaveType) {

        if (leaveType == null) {
            return null;
        }

        return new LeaveTypeDTO(
                leaveType.getLeaveTypeId(),
                leaveType.getLeaveTypeName(),
                leaveType.getDescription(),
                leaveType.getMaximumDays()
        );
    }

    public static LeaveType toEntity(LeaveTypeDTO dto) {

        if (dto == null) {
            return null;
        }

        LeaveType leaveType = new LeaveType();

        leaveType.setLeaveTypeId(dto.getLeaveTypeId());
        leaveType.setLeaveTypeName(dto.getLeaveTypeName());
        leaveType.setDescription(dto.getDescription());
        leaveType.setMaximumDays(dto.getMaximumDays());

        return leaveType;
    }
}