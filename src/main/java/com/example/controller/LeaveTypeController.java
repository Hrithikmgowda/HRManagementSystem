package com.example.controller;

import com.example.dto.LeaveTypeDTO;
import com.example.service.LeaveTypeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-types")
public class LeaveTypeController {

    private final LeaveTypeService leaveTypeService;

    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @PostMapping
    public ResponseEntity<LeaveTypeDTO> createLeaveType(
            @Valid @RequestBody LeaveTypeDTO leaveTypeDTO) {

        LeaveTypeDTO savedLeaveType =
                leaveTypeService.createLeaveType(leaveTypeDTO);

        return new ResponseEntity<>(
                savedLeaveType,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{leaveTypeId}")
    public ResponseEntity<LeaveTypeDTO> getLeaveTypeById(
            @PathVariable Long leaveTypeId) {

        LeaveTypeDTO leaveType =
                leaveTypeService.getLeaveTypeById(leaveTypeId);

        return ResponseEntity.ok(leaveType);
    }

    @GetMapping
    public ResponseEntity<List<LeaveTypeDTO>> getAllLeaveTypes() {

        List<LeaveTypeDTO> leaveTypes =
                leaveTypeService.getAllLeaveTypes();

        return ResponseEntity.ok(leaveTypes);
    }

    @PutMapping("/{leaveTypeId}")
    public ResponseEntity<LeaveTypeDTO> updateLeaveType(
            @PathVariable Long leaveTypeId,
            @Valid @RequestBody LeaveTypeDTO leaveTypeDTO) {

        LeaveTypeDTO updatedLeaveType =
                leaveTypeService.updateLeaveType(
                        leaveTypeId,
                        leaveTypeDTO
                );

        return ResponseEntity.ok(updatedLeaveType);
    }

    @DeleteMapping("/{leaveTypeId}")
    public ResponseEntity<Void> deleteLeaveType(
            @PathVariable Long leaveTypeId) {

        leaveTypeService.deleteLeaveType(leaveTypeId);

        return ResponseEntity.noContent().build();
    }
}