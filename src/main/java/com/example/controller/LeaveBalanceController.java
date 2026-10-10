package com.example.controller;

import com.example.dto.LeaveBalanceDTO;
import com.example.service.LeaveBalanceService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-balances")
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    public LeaveBalanceController(LeaveBalanceService leaveBalanceService) {
        this.leaveBalanceService = leaveBalanceService;
    }

    @PostMapping
    public ResponseEntity<LeaveBalanceDTO> createLeaveBalance(
            @Valid @RequestBody LeaveBalanceDTO leaveBalanceDTO) {

        LeaveBalanceDTO savedLeaveBalance =
                leaveBalanceService.createLeaveBalance(leaveBalanceDTO);

        return new ResponseEntity<>(
                savedLeaveBalance,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{balanceId}")
    public ResponseEntity<LeaveBalanceDTO> getLeaveBalanceById(
            @PathVariable Long balanceId) {

        LeaveBalanceDTO leaveBalance =
                leaveBalanceService.getLeaveBalanceById(balanceId);

        return ResponseEntity.ok(leaveBalance);
    }

    @GetMapping
    public ResponseEntity<List<LeaveBalanceDTO>> getAllLeaveBalances() {

        List<LeaveBalanceDTO> leaveBalances =
                leaveBalanceService.getAllLeaveBalances();

        return ResponseEntity.ok(leaveBalances);
    }

    @PutMapping("/{balanceId}")
    public ResponseEntity<LeaveBalanceDTO> updateLeaveBalance(
            @PathVariable Long balanceId,
            @Valid @RequestBody LeaveBalanceDTO leaveBalanceDTO) {

        LeaveBalanceDTO updatedLeaveBalance =
                leaveBalanceService.updateLeaveBalance(
                        balanceId,
                        leaveBalanceDTO
                );

        return ResponseEntity.ok(updatedLeaveBalance);
    }

    @DeleteMapping("/{balanceId}")
    public ResponseEntity<Void> deleteLeaveBalance(
            @PathVariable Long balanceId) {

        leaveBalanceService.deleteLeaveBalance(balanceId);

        return ResponseEntity.noContent().build();
    }
}