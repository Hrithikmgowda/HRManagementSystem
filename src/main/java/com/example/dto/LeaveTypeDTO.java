package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class LeaveTypeDTO {

    private Long leaveTypeId;

    @NotBlank(message = "Leave type name is required")
    private String leaveTypeName;

    private String description;

    @Positive(message = "Maximum leave days must be positive")
    private Integer maximumDays;

    public LeaveTypeDTO() {
    }

    public LeaveTypeDTO(Long leaveTypeId,
                        String leaveTypeName,
                        String description,
                        Integer maximumDays) {

        this.leaveTypeId = leaveTypeId;
        this.leaveTypeName = leaveTypeName;
        this.description = description;
        this.maximumDays = maximumDays;
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaximumDays() {
        return maximumDays;
    }

    public void setMaximumDays(Integer maximumDays) {
        this.maximumDays = maximumDays;
    }
}