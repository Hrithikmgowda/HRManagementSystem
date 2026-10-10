package com.example.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class LeaveBalanceDTO {

    private Long balanceId;

    @PositiveOrZero(message = "Total days cannot be negative")
    private Integer totalDays;

    @PositiveOrZero(message = "Used days cannot be negative")
    private Integer usedDays;

    @PositiveOrZero(message = "Remaining days cannot be negative")
    private Integer remainingDays;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    private String employeeName;

    @NotNull(message = "Leave type ID is required")
    private Long leaveTypeId;

    private String leaveTypeName;

    public LeaveBalanceDTO() {
    }

    public LeaveBalanceDTO(Long balanceId,
                           Integer totalDays,
                           Integer usedDays,
                           Integer remainingDays,
                           Long employeeId,
                           String employeeName,
                           Long leaveTypeId,
                           String leaveTypeName) {

        this.balanceId = balanceId;
        this.totalDays = totalDays;
        this.usedDays = usedDays;
        this.remainingDays = remainingDays;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.leaveTypeId = leaveTypeId;
        this.leaveTypeName = leaveTypeName;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getUsedDays() {
        return usedDays;
    }

    public void setUsedDays(Integer usedDays) {
        this.usedDays = usedDays;
    }

    public Integer getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(Integer remainingDays) {
        this.remainingDays = remainingDays;
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
}