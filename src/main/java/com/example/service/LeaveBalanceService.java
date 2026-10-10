package com.example.service;

import com.example.dto.LeaveBalanceDTO;

import java.util.List;

public interface LeaveBalanceService {

    LeaveBalanceDTO createLeaveBalance(LeaveBalanceDTO leaveBalanceDTO);

    LeaveBalanceDTO getLeaveBalanceById(Long balanceId);

    List<LeaveBalanceDTO> getAllLeaveBalances();

    LeaveBalanceDTO updateLeaveBalance(Long balanceId,
                                       LeaveBalanceDTO leaveBalanceDTO);

    void deleteLeaveBalance(Long balanceId);
}