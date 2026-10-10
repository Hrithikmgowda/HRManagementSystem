package com.example.controller;

import com.example.dto.LeaveRequestDTO;
import com.example.service.LeaveRequestService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequestDTO> createLeaveRequest(
            @Valid @RequestBody LeaveRequestDTO leaveRequestDTO) {

        LeaveRequestDTO savedLeaveRequest =
                leaveRequestService.createLeaveRequest(leaveRequestDTO);

        return new ResponseEntity<>(
                savedLeaveRequest,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{leaveRequestId}")
    public ResponseEntity<LeaveRequestDTO> getLeaveRequestById(
            @PathVariable Long leaveRequestId) {

        LeaveRequestDTO leaveRequest =
                leaveRequestService.getLeaveRequestById(leaveRequestId);

        return ResponseEntity.ok(leaveRequest);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaveRequests() {

        List<LeaveRequestDTO> leaveRequests =
                leaveRequestService.getAllLeaveRequests();

        return ResponseEntity.ok(leaveRequests);
    }

    @PutMapping("/{leaveRequestId}")
    public ResponseEntity<LeaveRequestDTO> updateLeaveRequest(
            @PathVariable Long leaveRequestId,
            @Valid @RequestBody LeaveRequestDTO leaveRequestDTO) {

        LeaveRequestDTO updatedLeaveRequest =
                leaveRequestService.updateLeaveRequest(
                        leaveRequestId,
                        leaveRequestDTO
                );

        return ResponseEntity.ok(updatedLeaveRequest);
    }

    @DeleteMapping("/{leaveRequestId}")
    public ResponseEntity<Void> deleteLeaveRequest(
            @PathVariable Long leaveRequestId) {

        leaveRequestService.deleteLeaveRequest(leaveRequestId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{leaveRequestId}/approve")
    public ResponseEntity<LeaveRequestDTO> approveLeaveRequest(
            @PathVariable Long leaveRequestId,
            @RequestParam String managerComment) {

        LeaveRequestDTO approvedLeaveRequest =
                leaveRequestService.approveLeaveRequest(
                        leaveRequestId,
                        managerComment
                );

        return ResponseEntity.ok(approvedLeaveRequest);
    }

    @PatchMapping("/{leaveRequestId}/reject")
    public ResponseEntity<LeaveRequestDTO> rejectLeaveRequest(
            @PathVariable Long leaveRequestId,
            @RequestParam String managerComment) {

        LeaveRequestDTO rejectedLeaveRequest =
                leaveRequestService.rejectLeaveRequest(
                        leaveRequestId,
                        managerComment
                );

        return ResponseEntity.ok(rejectedLeaveRequest);
    }
}